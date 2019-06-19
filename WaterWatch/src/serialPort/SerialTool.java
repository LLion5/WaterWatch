package serialPort;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.TooManyListenersException;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;

/**ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½
 */
public class SerialTool {

    private static SerialTool serialTool = null;

    static {
        //ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ClassLoaderï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½SerialToolï¿½ï¿½ï¿½ï¿½
        if (serialTool == null) {
            serialTool = new SerialTool();
        }
    }

    //ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½SerialToolï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½SerialToolï¿½ï¿½ï¿½ï¿½
    private SerialTool() {} 
    /**
     * ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½SerialToolï¿½ï¿½ï¿½ï¿½
     * @return serialTool
     */
    public static SerialTool getSerialTool() {

        if (serialTool == null) {
            serialTool = new SerialTool();
        }
        return serialTool;
    }
    /**
     * OKï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½
     * @return ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½
     */
    public static final List<String> findPort() {

        //ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½
        @SuppressWarnings("unchecked")
        Enumeration<CommPortIdentifier> portList = CommPortIdentifier.getPortIdentifiers(); 
        List<String> portNameList = new ArrayList<>();
        //ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½Listï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½List
        while (portList.hasMoreElements()) {
            String portName = portList.nextElement().getName();
            portNameList.add(portName);
        }
        return portNameList;
    }
    /**
     * OKï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½
     * @param portName ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½
     * @param baudrate ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½
     * @return ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½
     * @throws UnsupportedCommOperationException
     * @throws PortInUseException
     * @throws NoSuchPortException
     */
    public static final SerialPort openPort(String portName, int baudrate) throws UnsupportedCommOperationException, PortInUseException, NoSuchPortException {

        //ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½
        CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
        //ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½timeoutï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½
        CommPort commPort = portIdentifier.open(portName, 2000);
        //ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½
        if (commPort instanceof SerialPort) {
            SerialPort serialPort = (SerialPort) commPort;
            //ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½,ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½,ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½,ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½
            serialPort.setSerialPortParams(baudrate, SerialPort.DATABITS_8, SerialPort.STOPBITS_1 , SerialPort.PARITY_SPACE );                              
            return serialPort;
        }
        return null;
    }
    /**
     * OKï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½
     * @param serialport ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½
     */
    public static void closePort(SerialPort serialPort) {

        if (serialPort != null) {
            serialPort.close();
            serialPort = null;
        }
    }
    /**
     * OKï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½
     * @param serialPort ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½
     * @param order ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½
     * @throws IOException 
     */
    public static void sendToPort(SerialPort serialPort, byte[] order) throws IOException  {

        OutputStream out = null;
        out = serialPort.getOutputStream();
        out.write(order);
        out.flush();
        out.close();
    }
    
   
   
     public static String readPH(SerialPort serialPort) throws IOException{
    	InputStream in = null;
    	byte[] temp = new byte[9];
    	byte[] data = new byte[2];
    	in = serialPort.getInputStream();  
    	if(in.available() >= 9) {
    		in.read(temp);
    		data[0]=temp[5]; data[1]=temp[6];
    		String Buff = SerialTool.printHexString(data).trim();
        	return Buff;
    	}
    	return "";
    	
}
    
    public static String readTurbidity(SerialPort serialPort) throws IOException{
    	InputStream in = null;
    	byte[] temp = new byte[20];
    	in = serialPort.getInputStream(); 
    	System.out.println("×¼±¸¶ÁÈ¡Êý¾Ý");
    	if(in.available() >=14) {
    		System.out.println("¶ÁÈ¡Êý¾Ý³É¹¦");
    		in.read(temp);
    		String Buff = new String(temp).trim();
    		//String Buff = SerialTool.printHexString(temp).trim();
        	return  Buff.substring(6, 10);
    	}
    	return "";
    	
}
    
    public static String readPhAndTurbidity(SerialPort serialPort) throws IOException{
    	InputStream in = null;
    	byte[] temp = new byte[30];
    	in = serialPort.getInputStream(); 
    	//System.out.println("×¼±¸¶ÁÈ¡Êý¾Ý");
    	if(in.available() >=25) {
    		//System.out.println("¶ÁÈ¡Êý¾Ý³É¹¦");
    		in.read(temp);

    		String str = new String(temp).trim();
        	if(str.indexOf("V") == 15) {
        		 String PH =  SerialTool.printHexString(temp).trim().substring(16, 20);
        		 String Turbidity = str.substring(17, 21);
        		 float a = Float.parseFloat(Turbidity);
        		 float turbidity = (float)(-1120.4*a*a+5742.3*a-4352.9);
        		 float Ph = (float)(Integer.parseInt(PH, 16))/10;
        		 if(Ph  < 10) {
        			 return "PH:" + Float.toString(Ph) + "    Turbidity:" + Float.toString(turbidity); 
        		 }
        	}
    	}
    	return "";
    	
}

  public static String printHexString( byte[] b) {    
	  StringBuilder str = new StringBuilder();
	  int a;
    	   for (int i = 0; i < b.length; i++) {
    		   if(b[i]<0) {
    			   a = b[i] + 256;
    		   }
    		   else {
    			   a = b[i];
    		   }
    	     String hex = Integer.toHexString(b[i] & 0xFF);    
    	     if (hex.length() == 1) {    
    	       hex = '0' + hex;    
    	     }    
    	     str.append(hex);
    	     //System.out.print(hex.toUpperCase() );    
    	   }    
    	  return str.toString();
   }  
    
    public static byte[] readFromPort(SerialPort serialPort) throws IOException {

        InputStream in = null;
        byte[] bytes = null;
        try {
            in = serialPort.getInputStream();
            int bufflenth = in.available();
            while (bufflenth != 0) {                             
                bytes = new byte[bufflenth]; 
                in.read(bytes);
                bufflenth = in.available();
            } 
        } catch (IOException e) {
            throw e;
        } finally {
            if (in != null) {
                in.close();
                in = null;
            }
        }
        return bytes;
    }
  
    public static void addListener(SerialPort port, SerialPortEventListener listener) throws TooManyListenersException {

        port.addEventListener(listener);
        port.notifyOnDataAvailable(true);
        port.notifyOnBreakInterrupt(true);
    }
}