package eu.albertomorales.sshIntro;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class Prueba {

	public static void main(String[] args) {
		Prueba prueba = new Prueba();
		prueba.doIt();
	}

	private void doIt() {
		JSch jsch   = new JSch();
	    String ip   = "192.168.1.134";
	    String user = "tucho";
	    String pass = "tuputamadre";
	    int port    = 22;

	    try {
	        Session session = jsch.getSession(user, ip, port);
	        session.setPassword(pass);
	        session.setConfig("StrictHostKeyChecking", "no");
	        session.connect();

	        ChannelExec channel=(ChannelExec) session.openChannel("exec");
	        BufferedReader in=new BufferedReader(new InputStreamReader(channel.getInputStream()));
	        channel.setCommand("pwd;");
	        channel.connect();

	        String msg=null;
	        while((msg=in.readLine())!=null){
	          System.out.println(msg);
	        }

	        channel.disconnect();
	        session.disconnect();

	    } catch (Exception e) {
	    	e.printStackTrace();
	    }

	    System.out.println("YA'STA");
	}

}
