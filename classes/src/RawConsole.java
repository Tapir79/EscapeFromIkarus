

 import java.io.IOException; 
 import java.io.ByteArrayOutputStream; 
 import java.io.InputStream;
/**
 *
 * @author tapir
 */
public class RawConsole {

    private static String ttyConfig;

//    public static void main(String[] args) {
//
//            try {
//                    setTerminalToCBreak();
//
//                    int i=0;
//                    while (true) {
//
//                            System.out.println( ""+ i++ );
//                                    try {
//                                    Thread.sleep(100);
//                                    } catch (InterruptedException e1) {
//                                    e1.printStackTrace();
//                                    }
//                            if ( System.in.available() != 0 ) {
//                                    int c = System.in.read();
//                                    if ( c == 0x1B ) {
//                                            break;
//                                    }
//                            }
//
//                    } // end while
//            }
//            catch (IOException e) {
//                    System.err.println("IOException");
//            }
//            catch (InterruptedException e) {
//                    System.err.println("InterruptedException");
//            }
//            finally {
//                try {
//                    stty( ttyConfig.trim() );
//                 }
//                 catch (Exception e) {
//                     System.err.println("Exception restoring tty config");
//                 }
//            }
//
//    }

    public static void setTerminalToCBreak() throws IOException, InterruptedException {

        ttyConfig = stty("-g");

        // set the console to be character-buffered instead of line-buffered
        stty("-icanon min 1");

        // disable character echoing
        stty("-echo");
    }

    /**
     *  Execute the stty command with the specified arguments
     *  against the current active terminal.
     */
    public static String stty(final String args)
                    throws IOException, InterruptedException {
        String cmd = "stty " + args + " < /dev/tty";

        return exec(new String[] {
                    "sh",
                    "-c",
                    cmd
                });
    }

    /**
     *  Execute the specified command and return the output
     *  (both stdout and stderr).
     */
    public static String exec(final String[] cmd)
                    throws IOException, InterruptedException {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();

        Process p = Runtime.getRuntime().exec(cmd);
        int c;
        InputStream in = p.getInputStream();

        while ((c = in.read()) != -1) {
            bout.write(c);
        }

        in = p.getErrorStream();

        while ((c = in.read()) != -1) {
            bout.write(c);
        }

        p.waitFor();

        String result = new String(bout.toByteArray());
        return result;
    }

		
		
		
		
	
	

}
