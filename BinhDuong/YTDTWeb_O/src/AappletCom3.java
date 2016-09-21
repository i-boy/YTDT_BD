import java.applet.Applet;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.StringTokenizer;

import javax.comm.CommDriver;
import javax.comm.CommPort;
import javax.comm.CommPortIdentifier;
import javax.comm.PortInUseException;
import javax.comm.SerialPort;
import javax.comm.UnsupportedCommOperationException;

public class AappletCom3 extends Applet implements Runnable {
	// Define your thread.
	Thread commThread;

	boolean running = true;

	protected CommPortIdentifier selectedPortIdentifier;

	/** How long to wait for the open to finish up. */
	public static final int TIMEOUTSECONDS = 30;
	
	public static final String COM_NAME = "COM3";
	/** The baud rate to use. */
	public static final int BAUD = 9600;
	/** The chosen Port itself */
	CommPort thePort;

	OutputStream os = null;
	protected BufferedReader is;


	
	public void askPO() {

//		System.out.println("call askPO() ");
//		if (os == null) {
//			return;
//		}
//
//		try {
//			os.write("[P0S0000]".getBytes());
//			os.flush();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	public void pingpong(String str, String deviceNum){ //str: so thu tu ex. 12345
		//deviceNum = 0 or 1 or .....
		beginOperation();
		System.out.println("call pingpong()");
		System.out.println("str:" + str);
		if (os == null) {
			System.out.println("os:" + os);
			return;
		}
		if (str == null) {
			return;
		}
		try {
			String fistStr = "[L"+deviceNum+"F";
			System.out.println("firstStr:"+ fistStr);
			os.write(fistStr.getBytes());
//			os.write("[L0F".getBytes());
			
			StringTokenizer stk = new StringTokenizer(str,",");
			while (stk.hasMoreElements()){
				String s = (String)stk.nextElement();
				if (s!= null && !s.equals("")){
					try{
						Integer iS = Integer.parseInt(s);
						os.write(iS);
					}catch(Exception ex){
						char c = s.charAt(0);
						if (
								( c>=97 && c<=125 ) ||
								( c>=55 && c<=90)
						){
							int x = c-32;
							os.write(x);
						}
					}
					
				}
			}
			
			os.write(255);
			os.flush();
	
			Thread.sleep(2000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			endOperation();
		}
		endOperation();
	}

	public void outputToLed(String str, String deviceNum) { // THIS IS EXAMPLE:
		
		beginOperation();
		
		System.out.println("call outputToLed()");
		System.out.println("str:" + str);
		if (os == null) {
			System.out.println("os:" + os);
			return;
		}
		if (str == null) {
			return;
		}

		try {
			String fistStr = "[L"+deviceNum+"T";
			System.out.println("firstStr2:"+ fistStr);
			os.write(fistStr.getBytes());
//			os.write("[L0T".getBytes());
			
			StringTokenizer stk = new StringTokenizer(str,",");
			while (stk.hasMoreElements()){
				String s = (String)stk.nextElement();
				if (s!= null && !s.equals("")){
					try{
						Integer iS = Integer.parseInt(s);
						os.write(iS);
					}catch(Exception ex){
						char c = s.charAt(0);
						if (
								( c>=97 && c<=125 ) ||
								( c>=55 && c<=90)
						){
							int x = c-32;
							os.write(x);
						}
					}
					
				}
			}
			
			

			os.write(255);
			os.flush();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			endOperation();
		}
		endOperation();

	}
	
	private void beginOperation(){// portName = COM1 or something like that
		
	}
	private void endOperation(){
		//destroy();
	}
	public void init() {
		System.out.println("thanh - init - myInitialize()");
		myInitialize();
		//do nothing
	}
	public void myInitialize() { 

		String drivername = "com.sun.comm.Win32Driver";

		try {
			CommDriver driver = (CommDriver) Class.forName(drivername)
					.newInstance();
			driver.initialize();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		

		System.out.println("call init() ");
		Enumeration pList = CommPortIdentifier.getPortIdentifiers();
		while (pList.hasMoreElements()) {
			CommPortIdentifier cpi = (CommPortIdentifier) pList.nextElement();
			if (cpi.getPortType() == CommPortIdentifier.PORT_SERIAL) {
				selectedPortIdentifier = cpi;
			}
			System.out.println("cpi.getName(): " + cpi.getName());
			if (cpi.getName() != null && cpi.getName().equals(COM_NAME)) {
				break;
			}
		}
		
		
		System.out.println("selectedPortIdentifier: " + selectedPortIdentifier);
		if (selectedPortIdentifier == null) {
			return;
		}
		try 
		{
			
			thePort = selectedPortIdentifier.open("DarwinSys DataComm",
					TIMEOUTSECONDS * 100);
		
		
			SerialPort myPort = (SerialPort) thePort;
		
			// set up the serial port

			myPort.setSerialPortParams(BAUD, SerialPort.DATABITS_8,
					SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);

			os = myPort.getOutputStream();
			is = new BufferedReader(new InputStreamReader(myPort
					.getInputStream()));

			System.out.println("os: " + os);
			System.out.println("is: " + is);
} catch (PortInUseException e) {
	e.printStackTrace();
	return;
		
		} catch (UnsupportedCommOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
//		// Create the thread.
//		commThread = new Thread(this);
//		// and let it start running
//		commThread.start();
		
		// need to call javascript to 
	}

	// Very important. You do not want your thread to keep running when
	// the applet is deactivated (eg. user left page)
	public void destroy() {
		// Finally, clean up.
		System.out.println("Finally, clean up.");
		if (is != null)
			try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		if (os != null) {
			try {
				os.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		SerialPort myPort = (SerialPort) thePort;
		if (myPort != null){
			myPort.close();
		}
		// will cause thread to stop looping
		//running = false;
		// destroy it.
		//commThread = null;
	}

	// The method that will be called when you have a thread.
	// You always need this when you implement Runnable (use a thread)
	public void run() {
		// loop until told to stop
		while (running) {

//			System.out.println("----2");
//			CharBuffer chr = CharBuffer.allocate(255);
//			try {
//				is.read(chr);
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//			System.out.println("value:" + new String(chr.array()) + ".");
//			// xu ly trong trong hop uu tien goi tu ban phim PO
//
//			// end
//
//			// Now the reason for threads
//			try {
//				// Wait 500milliseconds before continuing
//				commThread.sleep(500);
//			} catch (InterruptedException e) {
//				System.out.println(e);
//			}
//			// he has wait and will now restart his actions.
		}
	}
}