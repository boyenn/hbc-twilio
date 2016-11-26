package be.boyenvaesen.hbctwilio.handlers;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Boyen on 26/11/2016.
 */
public class MessageHandlerTest {
    @Test
    public void isRegistrationMessage() throws Exception {
        MessageHandler m = new MessageHandler("test", "BRIDGE Boyen Vaesen 03/06/1996 JA");
        assertThat(m.isRegistrationMessage()).isTrue();
        m = new MessageHandler("test", "BRIDGwE Boyen Vaesen 03/06/1996 JA");
        assertThat(m.isRegistrationMessage()).isFalse();
        m = new MessageHandler("test", "BRIDGE Boyen Vaesen 50/06/1996 JA");
        assertThat(m.isRegistrationMessage()).isFalse();
        m = new MessageHandler("test", "BRIDGE Boyen Vaesen 03/06/1996 JA RANDOMSTUFF");
        assertThat(m.isRegistrationMessage()).isFalse();
        m = new MessageHandler("test", "BRIDGE Boyen Vaesen 03/06/1996 NEE");
        assertThat(m.isRegistrationMessage()).isTrue();
        m = new MessageHandler("test", "BRIDGE Boyen Vaesen 03/06/1996 NEEN");
        assertThat(m.isRegistrationMessage()).isFalse();
        m = new MessageHandler("test", "BRIDGE Boyen Vaesen 03/FD/1996 NEE");
        assertThat(m.isRegistrationMessage()).isFalse();
    }

}