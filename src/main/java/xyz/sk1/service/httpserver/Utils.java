package xyz.sk1.service.httpserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Utils {

    public static final Logger LOG = LoggerFactory.getLogger(SimpleHttpServer.class);

    public enum Socket {
        MAXIMUM_PORT(65535);

        private int maxPort;

        Socket(int maxInteger){
            maxPort = maxInteger;
        }

        public int getMax() {
            return maxPort;
        }
    }

}
