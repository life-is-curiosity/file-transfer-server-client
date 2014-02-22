import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class FileSenderClient {

	public void client() throws UnknownHostException,
			IOException {

		int filesize = 6022386;
		String fileType = "";
		long start = System.currentTimeMillis();
		int bytesRead;
		int current = 0;

		Socket sock = new Socket("127.0.0.1", 13267);
		System.out.println("Connecting...");

		// receive file
		byte[] mybytearray = new byte[filesize];
		InputStream is = sock.getInputStream();
		FileOutputStream fos = new FileOutputStream(
				"C:/Users/Gabriel-Alan/Desktop/file."+fileType);
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		bytesRead = is.read(mybytearray, 0, mybytearray.length);
		current = bytesRead;

		do {
			bytesRead = is.read(mybytearray, current,
					(mybytearray.length - current));
			if (bytesRead >= 0)
				current += bytesRead;
		} while (bytesRead > -1);

		bos.write(mybytearray, 0, current);
		bos.flush();
		long end = System.currentTimeMillis();
		System.out.println(end - start);
		bos.close();
		sock.close();
	}
}
