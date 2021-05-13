package phonehome;

/**
 * Fundamental methods for the client side of PhoneHome.
 * 
 * @author Stevie Alvarez
 */
public interface PhoneLine {
    
    /**
     * Get the user's desired name to be displayed while they're connected.
     * 
     * @return The user's desired name.
     */
    public default String getUserName() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("getUserName is unimplemented!");
    }

    /**
     * Recieve and handle input.
     */
    public abstract void recieve();

    /**
     * Send a message.
     * 
     * @param msg Message to send.
     */
    public abstract void transmit(String msg);
}
