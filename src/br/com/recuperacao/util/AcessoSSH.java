package br.com.recuperacao.util;

import java.io.BufferedReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class AcessoSSH {
	
	private static final String REMOTE_HOST = "127.0.0.1";
	private static final String REMOTE_USER = "senac";
	private static final String REMOTE_PASSWORD = "senac@123";
	private static final int REMOTE_PORT = 2222;
	
	
	private static final int SESSION_TIMEOUT = 10000;
	private static final int CHANNEL_TIMEOUT = 10000;
	
	public static void executar() {
		String comando = "python3 /home/senac/Python";
		Session sessao = null;
		
		try {
			
			Properties config = new Properties();
			
			config.put("StrictHostKeyChecking", "no");
			
			JSch jsch = new JSch();
			
			jsch.setKnownHosts("/home/senac/.ssh/nome_arquivo_chave");
			
			sessao = jsch.getSession(REMOTE_USER, REMOTE_HOST, REMOTE_PORT);
			sessao.setPassword(REMOTE_PASSWORD);
			
			sessao.setConfig(config);
			
			sessao.connect(SESSION_TIMEOUT);
			
			ChannelExec channelExec = (ChannelExec) sessao.openChannel("exec");
			
			channelExec.setErrStream(System.err);
			
			channelExec.setCommand(comando);
			
			InputStream in = channelExec.getInputStream();
			
			channelExec.connect(CHANNEL_TIMEOUT);

			byte[] tmp = new byte[1024];
			
			while(true) {
				while(in.available()>0) {
					int i = in.read(tmp,0,1024);
					if(i < 0) break;
					System.out.println(new String(tmp,0,i));
				}
				
				if(channelExec.isClosed()) {
					if(in.available()>0) continue;
					System.out.println("Saída do processo "+channelExec.getExitStatus());
					break;
				}
				try {
					Thread.sleep(1000);
				}
				catch(Exception ex) {
					ex.printStackTrace();
				}
			}
			
			Process pr = Runtime.getRuntime().exec("C:\\Program Files\\Mozilla Firefox\\firefox.exe http://127.0.0.1:5500/dep_vlr.html");
			BufferedReader leitor = new BufferedReader(new InputStreamReader(pr.getInputStream()));
			
			String rs = "";
			
			while((rs = leitor.readLine())!= null){
				System.out.println(rs);
			}
			channelExec.disconnect();
		}
		catch(JSchException jsex) {
			jsex.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			if(sessao != null) {
				sessao.disconnect();
			}
		}	
	}

}

