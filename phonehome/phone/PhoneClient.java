package phonehome.phone;

import java.io.IOException;
import java.net.Socket;

import phonehome.Comm;
import phonehome.PhoneLine;

/**
 * Client side of HomePhone. Sends and recieves messages from the Home server.
 * 
 * Reference for a typical run:
 *      Get username
 *      Send username to server
 *      Start thread to recieve and display messages
 *      Start thread to get messages from user and send them
 * 
 * @author Stevie Alvarez
 */
public class PhoneClient extends Comm implements PhoneLine {

    public PhoneClient(Socket socket) throws IOException {
        super(socket);
    }


    @Override
    public String getUserName() {
        return null;
    }


    @Override
    public void recieve() {
        // TODO Auto-generated method stub
        
    }


    @Override
    public void transmit(String msg) {
        // TODO Auto-generated method stub
        
    }


    @Override
    public void run() {
        // TODO Auto-generated method stub
        
    }

    public static void main(String[] args) {
        
    }
}
