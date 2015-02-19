package org.openid.hs.embeddedprocess;

import java.io.File;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import com.mongodb.MongoClient;

import de.flapdoodle.embed.mongo.Command;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.ArtifactStoreBuilder;
import de.flapdoodle.embed.mongo.config.DownloadConfigBuilder;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongoCmdOptionsBuilder;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.config.RuntimeConfigBuilder;
import de.flapdoodle.embed.mongo.config.processlistener.ProcessListenerBuilder;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.config.IExecutableProcessConfig;
import de.flapdoodle.embed.process.config.IRuntimeConfig;
import de.flapdoodle.embed.process.config.io.ProcessOutput;
import de.flapdoodle.embed.process.extract.UUIDTempNaming;
import de.flapdoodle.embed.process.io.Processors;
import de.flapdoodle.embed.process.io.directories.FixedPath;
import de.flapdoodle.embed.process.io.directories.IDirectory;
import de.flapdoodle.embed.process.io.directories.PropertyOrPlatformTempDir;
import de.flapdoodle.embed.process.io.file.Files;
import de.flapdoodle.embed.process.runtime.Executable;
import de.flapdoodle.embed.process.runtime.IStopable;
import de.flapdoodle.embed.process.runtime.Network;

/**
 * Mongo embedded process.
 * @see AbstractEmbeddedProcess
 * @version R3
 * @author Steven, Victor
 *
 */
public class MongoEmbeddedProcess extends AbstractEmbeddedProcess {
	/**
	 * Type of this embedded process.
	 */
	public static final int TYPE = 1;
	/**
	 * Mongo dirname.
	 */
	public static final String MONGO_DIRNAME = ".embeddedprocess/mongodb";
	/**
	 * Mongo data dirname.
	 */
	public static final String MONGO_DATA_DIRNAME = String.format("%s/data",
			MONGO_DIRNAME);
	/**
	 * All mongo client created.
	 */
	private static Map<Integer, MongoClient> clients = new HashMap<Integer, MongoClient>();

	/**
	 * Returns mongo client.
	 * @param pPort Port of mongo server.
	 * @return Mongo client.
	 * @throws UnknownHostException
	 */
	public static MongoClient getClient(int pPort) throws UnknownHostException {
		if (!clients.containsKey(pPort)) {
			clients.put(pPort, new MongoClient("localhost", pPort));
		}
		MongoClient client = clients.get(pPort);
		return client;
	}
	/**
	 * Closes mongo clients.
	 * @param pPort Port of client to closed.
	 */
	public static void closeClient(int pPort) {
		if (clients.containsKey(pPort)) {
			MongoClient client = clients.get(pPort);
			client.close();
			clients.remove(pPort);
		}
	}

	/**
	 * Mongo server.
	 */
	private Executable<? extends IExecutableProcessConfig, ? extends IStopable> executable;
	/**
	 * Shudown interface.
	 */
	private IStopable stopable;

	public MongoEmbeddedProcess(int pPort) throws EmbeddedProcessException {
		super(TYPE, pPort);
	}
	@Override
	public void initProcess() throws EmbeddedProcessException {
		try {

			for (String lib : new String[] {
					String.format("extract-%s-mongod.pid",
							System.getProperty("user.name")),
					String.format("extract-%s-mongod.exe",
							System.getProperty("user.name")) }) {
				Files.forceDelete(new File(PropertyOrPlatformTempDir
						.defaultInstance().asFile(), lib));
			}

			MongodConfigBuilder mongodConfigBuilder = new MongodConfigBuilder()
					.version(Version.Main.PRODUCTION).net(
							new Net(getPort(), Network.localhostIsIPv6()));

			File data = Files.createOrCheckUserDir(MONGO_DATA_DIRNAME);
			mongodConfigBuilder.processListener(
					new ProcessListenerBuilder()
							.copyFilesIntoDbDirBeforeStarFrom(data)
							.copyDbFilesBeforeStopInto(data).build())
					.cmdOptions(
							new MongoCmdOptionsBuilder().defaultSyncDeplay()
									.build());

			IMongodConfig config = mongodConfigBuilder.build();

			Command command = Command.MongoD;

			ProcessOutput processOutput;
			if (SILENT_LOGGING) {
				processOutput = ProcessOutput.getDefaultInstanceSilent();
			} else {
				processOutput = new ProcessOutput(
						Processors.namedConsole("[mongod]"),
						Processors.namedConsole("[mongod alert]"),
						Processors.namedConsole("[mongod console]"));
			}

			IDirectory artifactStorePath = new FixedPath(String.format("%s/%s",
					System.getProperty("user.home"), MONGO_DIRNAME));

			IRuntimeConfig runtimeConfig = new RuntimeConfigBuilder()
					.defaults(command)
					.processOutput(processOutput)
					.artifactStore(
							new ArtifactStoreBuilder()
									.defaults(command)
									//.executableNaming(new UserTempNaming())
									.executableNaming(new UUIDTempNaming())
									.download(
											new DownloadConfigBuilder()
													.defaultsForCommand(command)
													.artifactStorePath(
															artifactStorePath)))
					.build();

			MongodStarter runtime = MongodStarter.getInstance(runtimeConfig);

			executable = runtime.prepare(config);
		} catch (Exception e) {
			throw new EmbeddedProcessException(e);
		}
	}
	@Override
	public void start() throws EmbeddedProcessException {
		try {
			stopable = executable.start();
		} catch (Exception e) {
			throw new EmbeddedProcessException(e);
		}
	}
	@Override
	public void stop() throws EmbeddedProcessException {
		closeClient(getPort());
		//executable.stop();
		stopable.stop(); 
	}
}
