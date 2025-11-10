package com.ziotic.adapter.protocol;
 

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.future.IoFutureListener;
import org.apache.mina.core.session.IoSession;

import com.ziotic.Static;
import com.ziotic.network.FrameBuilder;
import com.ziotic.utility.Logging;
import com.ziotic.utility.Streams;
import com.ziotic.utility.crypt.XTEA;
 
/**
 * @author Seth Rogen
 */
 
 
public class AccountConnectCodeC {
	
    private static final Logger logger = Logging.log();
    
    public static void decodeDetails(IoSession session, IoBuffer buffer) { 
    	System.out.println("Decode Connection");
        int rsaBlockSize, rsaHeaderKey, lang;
        String password, email;
        boolean subscribe;
        int[] xteaKey;
        byte[] xteaBlock;
        long userFlow;
        XTEA xtea;
        IoBuffer buffer2;
        	int size = buffer.getShort(); //PacketSize
            if (size > buffer.remaining()) {
                return;
            }
            int clientRevision = buffer.getShort();
            if (clientRevision != Static.clientConf.getClientVersion()) {
                //
            }
            rsaBlockSize = buffer.getShort(); // RSA block size
            if (rsaBlockSize > buffer.remaining()) {
                return;
            }
            rsaHeaderKey = buffer.get(); // RSA header key
            if (rsaHeaderKey != 10) {
                logger.warn("Incorrect RSA header key: " + rsaHeaderKey);
            }
            xteaKey = new int[4];
            for (int i = 0; i < xteaKey.length; i++) {
                xteaKey[i] = buffer.getInt();
            }
            buffer.skip(4 * 10 + 2);
            xteaBlock = new byte[buffer.remaining()];
            buffer.get(xteaBlock);
            xtea = new XTEA(xteaKey);
            xtea.decrypt(xteaBlock, 0, xteaBlock.length);
            buffer2 = IoBuffer.wrap(xteaBlock);
            email = Streams.readString(buffer2);
            lang = buffer2.get();
            /**
             * Checks to make sure its a actual email or not.
             */
           // if(!isValidEmail(email)) { 
            	//session.write(new FrameBuilder(1).writeByte(21).toFrame()).addListener(IoFutureListener.CLOSE);
           // }
            session.write(new FrameBuilder(1).writeByte(2).toFrame()).addListener(IoFutureListener.CLOSE);
    	}
}