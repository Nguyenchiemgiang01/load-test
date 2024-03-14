package Test;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutionException;

import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import database.Threadreqdata;

import javax.swing.ListSelectionModel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Box;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JList;
import java.awt.ScrollPane;
import javax.swing.JTree;
import javax.swing.JTextArea;
import javax.swing.JTabbedPane;

public class main {

	private JFrame frame;
	private final JLabel lblNewLabel = new JLabel("Protocol:");
	private JTextField txtip;
	private JTextField txtprotocol;
	private JTextField txtpath;
	private JTextField txtmethod;
	private JTextField txtnumthread;
	private JTextField txtrampup;
	private JTextArea textArea;
	private JLabel lblBodyData;
	private JPanel panel_1;
	private JTextField txtdataurl;
	private JTextField txtdriver;
	private JTextField txtusername;
	private JTextField txtpassword;
	private JTextField txtmaxconnect;
	private JTextField txtmaxwait;
	private JTextField txtthreads;
	private JLabel lblquery;
	private JTextField txtquery;
	private JPanel summary ;
	private JPanel responsedata;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main window = new main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	
		
		
	}

	/**
	 * Create the application.
	 */
	public main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(50, 50, 1200, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 1013, 566);
		
		panel.setLayout(null);
		
		
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(524, 37, 80, 25);
		//frame.getContentPane().add(txtprotocol);
		
		JLabel lblIp = new JLabel("IP(server name):");
		lblIp.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblIp.setBounds(10, 37, 150, 25);
		//frame.getContentPane().add(lblIp);
		
		txtpath = new JTextField("SmartShop-master/xuly-dangnhap.php");
		txtpath.setColumns(10);
		txtpath.setBounds(199, 94, 186, 63);
		//frame.getContentPane().add(txtpath);
		
		JLabel lblPath = new JLabel("Path:");
		lblPath.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPath.setBounds(10, 113, 46, 25);
		//frame.getContentPane().add(lblPath);
		
		JLabel lblMethod = new JLabel("Method:");
		lblMethod.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblMethod.setBounds(524, 120, 73, 25);
		//frame.getContentPane().add(lblMethod);
		
		txtmethod = new JTextField("POST");
		txtmethod.setColumns(10);
		txtmethod.setBounds(705, 105, 150, 41);
		//frame.getContentPane().add(txtmethod);
		
		JLabel lblNumberthread = new JLabel("Number Thread");
		lblNumberthread.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNumberthread.setBounds(0, 190, 139, 25);
		//frame.getContentPane().add(lblNumberthread);
		
		txtnumthread = new JTextField("700");
		txtnumthread.setColumns(10);
		txtnumthread.setBounds(195, 189, 190, 36);
		//frame.getContentPane().add(txtnumthread);

		
		JButton btnNewButton = new JButton("OK");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(527, 242, 104, 56);
		//frame.getContentPane().add(btnNewButton);
		panel.add(btnNewButton);
		JLabel lblRampup = new JLabel("Ramp-up period(s)");
		lblRampup.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblRampup.setBounds(527, 190, 168, 25);
		//frame.getContentPane().add(lblRampup);
		
		txtrampup = new JTextField("0");
		txtrampup.setColumns(10);
		txtrampup.setBounds(705,186,150,41);
		//frame.getContentPane().add(txtrampup);
		
		textArea = new JTextArea("username=kh1&password=123");
		textArea.setBounds(146, 246, 360, 150);
		//frame.getContentPane().add(textArea);
		
		lblBodyData = new JLabel("Body data:");
		lblBodyData.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblBodyData.setBounds(10, 242, 96, 25);
		
		
		
		
		
		
		
		
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
						String protocol= new String (txtprotocol.getText());
						String ip= new String(txtip.getText());
						String path= new String(txtpath.getText());
						String method= new String(txtmethod.getText());
						String numberthread= new String(txtnumthread.getText());
						Long ramup = Long.valueOf(txtrampup.getText());
						String post_parras= new String(textArea.getText());
						
						ThreadRequest threadRequest= new ThreadRequest(protocol, ip, method, path,Integer.valueOf(numberthread),ramup,post_parras);
						List<List<Long>> list = new ArrayList<List<Long>>();
						try {
							list = ThreadRequest.request();
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (ExecutionException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
//						String[] data= new String[Integer.valueOf(numberthread)];
//						list.toArray(data);		
//						Object[] objects= new Object[Integer.valueOf(numberthread)];
						String col[] = {"name","time response","Time start","Status"};
						String[][] row = new String[Integer.parseInt(numberthread)][4];
						JTable table = new JTable(row,col);
						

						String sumary_col[] = {"Total","Min","Max","Average","Throughput","ERROR %"};
						String[][] sumary_row = new String[1][6];
						JTable table_sumary = new JTable(sumary_row,sumary_col);
						int nsuccess = 0;
						for(int i=0;i<Integer.parseInt(numberthread);i++)
						{
							row[i][0] = "thread " + i;
							row[i][1] = String.valueOf(list.get(i).get(0));
							row[i][2]= 	String.valueOf(list.get(i).get(1));
							long status_code = list.get(i).get(2);
							if(status_code>199 && status_code<300) {
								row[i][3] = "Success";
								nsuccess += 1;
								}
							else if(status_code>399 && status_code<500) {
								row[i][3] = "Client ERROR";
							}
							else if(status_code>499  && status_code<600) {
								row[i][3] = "Server ERROR";
							}
							else {
								row[i][3] = "Can't connect";
							}
						}
						//error %
						double errorpercent = 1 - (double)nsuccess/Integer.parseInt(numberthread);
						long max_ts_t=Long.valueOf(row[0][2])+Long.valueOf(row[0][1]);
						long min_ts_t=Long.valueOf(row[0][2])+Long.valueOf(row[0][1]);
						
						long max_t=Long.valueOf(row[0][1]);
						long min_t=Long.valueOf(row[0][1]);
						
						long min_ts=Long.valueOf(row[0][2]);
						
						Double Sum=0.0;
						for(int i=0;i<Integer.parseInt(numberthread);i++) {
							max_ts_t=Math.max(max_ts_t,Long.valueOf(row[i][2])+Long.valueOf(row[i][1]) );
							min_ts_t=Math.min(min_ts_t,Long.valueOf(row[i][2])+Long.valueOf(row[i][1]) );
							
							max_t=Math.max(max_t,Long.valueOf(row[i][1]) );
							min_t=Math.min(min_t,Long.valueOf(row[i][1]) );
							
							min_ts=Math.min(min_ts,Long.valueOf(row[i][2]) );
							
							Sum+=Long.valueOf(row[i][1]);
						}
						
						
						
						
						double throughput=1000*Integer.parseInt(numberthread)*1.0/(max_ts_t-min_ts);	
//						System.out.print(throughput);
//						System.out.print(Latency);
						
						
						sumary_row[0][0]=numberthread;
						sumary_row[0][1]=String.valueOf(min_t);
						sumary_row[0][2]=String.valueOf(max_t);
						sumary_row[0][3]=String.valueOf(Sum/Integer.parseInt(numberthread));
						sumary_row[0][4]=String.valueOf(throughput);
						sumary_row[0][5]=String.valueOf(errorpercent*100);
						
						JScrollPane sp = new JScrollPane(table);
						sp.setBounds(50, 400, 900, 150);
						////frame.getContentPane().add(sp);
						panel.add(sp);
						
						JScrollPane Summary_Report = new JScrollPane(table_sumary);
						Summary_Report.setBounds(50, 600, 900, 50);
						//frame.getContentPane().add(Summary_Report);
						panel.add(Summary_Report);
						;
						
			}
		});
		
		panel.add(txtmethod);
		panel.add(txtpath);
		panel.add(txtrampup);
		panel.add(lblNewLabel);
		panel.add(lblBodyData);
		panel.add(lblRampup);
		panel.add(lblMethod);
		panel.add(lblNumberthread);
		panel.add(lblPath);
		panel.add(txtnumthread);
		panel.add(textArea);
		
		//frame.getContentPane().add(lblNewLabel);
		
		txtip = new JTextField("localhost");
		txtip.setBounds(199, 33, 186, 37);
		//frame.getContentPane().add(txtip);
		txtip.setColumns(10);
		panel.add(txtip);
		
		txtprotocol = new JTextField("http");
		txtprotocol.setColumns(10);
		txtprotocol.setBounds(705, 35, 150, 37);
		panel.add(txtprotocol);
		panel.add(lblIp);
		panel.add(lblBodyData);
		
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tabbedPane.setBounds(10, 10, 1166, 743);
		tabbedPane.addTab("http",panel);
		frame.getContentPane().add(tabbedPane);
		
		panel_1 = new JPanel();
		tabbedPane.addTab("Database", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lbldataurl = new JLabel("Database URL");
		lbldataurl.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbldataurl.setBounds(21, 50, 158, 39);
		panel_1.add(lbldataurl);
		
		txtdataurl = new JTextField("jdbc:mysql://localhost:3306/smartshop");
		txtdataurl.setBounds(191, 56, 216, 36);
		panel_1.add(txtdataurl);
		txtdataurl.setColumns(10);
		
		JLabel lbldriver = new JLabel("JDBC Driver Class");
		lbldriver.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbldriver.setBounds(21, 119, 158, 39);
		panel_1.add(lbldriver);
		
		txtdriver = new JTextField("com.mysql.jdbc.Driver");
		txtdriver.setColumns(10);
		txtdriver.setBounds(191, 119, 216, 36);
		panel_1.add(txtdriver);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUsername.setBounds(21, 182, 158, 39);
		panel_1.add(lblUsername);
		
		JLabel lblPassWord = new JLabel("password");
		lblPassWord.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPassWord.setBounds(21, 231, 158, 39);
		panel_1.add(lblPassWord);
		
		txtusername = new JTextField("root");
		txtusername.setColumns(10);
		txtusername.setBounds(191, 182, 216, 36);
		panel_1.add(txtusername);
		
		txtpassword = new JTextField();
		txtpassword.setColumns(10);
		txtpassword.setBounds(191, 231, 216, 36);
		panel_1.add(txtpassword);
		
		JLabel lblmaxconnect = new JLabel("Max Connection");
		lblmaxconnect.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblmaxconnect.setBounds(498, 50, 158, 39);
		panel_1.add(lblmaxconnect);
		
		txtmaxconnect = new JTextField("15");
		txtmaxconnect.setColumns(10);
		txtmaxconnect.setBounds(666, 50, 216, 36);
		panel_1.add(txtmaxconnect);
		
		JLabel lblMaxWait = new JLabel("Max Wait");
		lblMaxWait.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblMaxWait.setBounds(499, 119, 158, 39);
		panel_1.add(lblMaxWait);
		
		txtmaxwait = new JTextField("6000");
		txtmaxwait.setColumns(10);
		txtmaxwait.setBounds(666, 125, 216, 36);
		panel_1.add(txtmaxwait);
		
		JButton btnrqdata = new JButton("OK");
		btnrqdata.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnrqdata.setBounds(186, 284, 111, 47);
		btnrqdata.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String databaseURL=txtdataurl.getText();
				String jdbcdriverclass=txtdriver.getText();
				String username=txtusername.getText();
				String password="";
				password=txtpassword.getText()==null?"":txtpassword.getText();
				String maxconnection=txtmaxconnect.getText();
				int maxwait= Integer.parseInt(txtmaxwait.getText()) ;
				int numberthread=Integer.parseInt(txtthreads.getText()) ;
				String query= txtquery.getText();
				
				Threadreqdata threadreqdata= new Threadreqdata(databaseURL,jdbcdriverclass,username,password,Integer.parseInt(maxconnection),maxwait,numberthread,query);
				//Exception ex= threadreqdata.exceptions;
				
				ArrayList<List<String>> results= new ArrayList<List<String>>();
				results=null;
				
					try {
						try {
							results=threadreqdata.requestdata();
						} catch (ExecutionException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			
				String col[] = {"name","time response","Time start","Status"};
				String[][] row = new String[numberthread][4];
				JTable table = new JTable(row,col);
				

				String sumary_col[] = {"Total","Min","Max","Average","Error(%)","Throughput"};
				String[][] sumary_row = new String[1] [6];
				JTable table_sumary = new JTable(sumary_row,sumary_col);
				
				String dataresponse_col[]= {"name","dataresponse"};
				String[][] dataresponse_row= new String[numberthread][2];
				JTable table_responsedata=new JTable(dataresponse_row,dataresponse_col);
				int error=0;
				for(int i=0;i<numberthread;i++)
				{
					
					row[i][0] = "thread " + i;
					row[i][1] = String.valueOf(results.get(i).get(0));
					row[i][2]= 	String.valueOf(results.get(i).get(1));
					
					
					
					dataresponse_row[i][0]="thread"+i;
					//System.out.print(results.size()+"\n");
					
					dataresponse_row[i][1]="";
					for (int j=2;j<results.get(i).size();j++) {
						
						dataresponse_row[i][1]+=String.valueOf(results.get(i).get(j));
					
					}
					
					if(dataresponse_row[i][1].toString().compareTo("can't connect")!=0) {
						row[i][3]=dataresponse_row[i][1].toString();
					}
					else {
						row[i][3]=dataresponse_row[i][1].toString();
						error++;
					}
					
					
				}
				System.out.print("error:"+error+"");
				
				
				long max_ts_t=Long.valueOf(row[0][2])+Long.valueOf(row[0][1]);
				long min_ts_t=Long.valueOf(row[0][2])+Long.valueOf(row[0][1]);
				
				long max_t=Long.valueOf(row[0][1]);
				long min_t=Long.valueOf(row[0][1]);
				
				long min_ts=Long.valueOf(row[0][2]);
				
				Double Sum=0.0;
				for(int i=0;i<numberthread;i++) {
					max_ts_t=Math.max(max_ts_t,Long.valueOf(row[i][2])+Long.valueOf(row[i][1]) );
					min_ts_t=Math.min(min_ts_t,Long.valueOf(row[i][2])+Long.valueOf(row[i][1]) );
					
					max_t=Math.max(max_t,Long.valueOf(row[i][1]) );
					min_t=Math.min(min_t,Long.valueOf(row[i][1]) );
					
					min_ts=Math.min(min_ts,Long.valueOf(row[i][2]) );
					
					Sum+=Long.valueOf(row[i][1]);
				}
				
				

				
				double throughput=1000*numberthread*1.0/(max_ts_t-min_ts);	
				
				
				sumary_row[0][0]=String.valueOf(numberthread);
				sumary_row[0][1]=String.valueOf(min_t);
				sumary_row[0][2]=String.valueOf(max_t);
				sumary_row[0][3]=String.valueOf(Sum/numberthread);
				sumary_row[0][4]=String.valueOf(100*error/numberthread);
				sumary_row[0][5]=String.valueOf(throughput);
			
			
				
				JScrollPane sp = new JScrollPane(table);
				sp.setBounds(50,50, 900, 150);
				////frame.getContentPane().add(sp);
				summary.add(sp);
				
				JScrollPane Summary_Report = new JScrollPane(table_sumary);
				Summary_Report.setBounds(50, 250, 900, 50);
				//frame.getContentPane().add(Summary_Report);
				summary.add(Summary_Report);

				JScrollPane response_data = new JScrollPane(table_responsedata);
				sp.setBounds(50,50, 900, 150);
				responsedata.add(response_data);
			}
		});
		panel_1.add(btnrqdata);
		
		JLabel lblthread = new JLabel("Number Thread");
		lblthread.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblthread.setBounds(472, 182, 158, 39);
		panel_1.add(lblthread);
		
		txtthreads = new JTextField();
		txtthreads.setColumns(10);
		txtthreads.setBounds(666, 182, 216, 36);
		panel_1.add(txtthreads);
		
		lblquery = new JLabel("query");
		lblquery.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblquery.setBounds(472, 231, 158, 39);
		panel_1.add(lblquery);
		
		txtquery = new JTextField();
		txtquery.setColumns(10);
		txtquery.setBounds(554, 234, 428, 94);
		panel_1.add(txtquery);
		
		JTabbedPane viewdatatest = new JTabbedPane(JTabbedPane.TOP);
		viewdatatest.setFont(new Font("Tahoma", Font.PLAIN, 18));
		viewdatatest.setToolTipText("");
		viewdatatest.setBounds(21, 367, 1050, 340);
		panel_1.add(viewdatatest);
		
		summary = new JPanel();
		summary.setBounds(380, 318, 101, 13);
		viewdatatest.add("sumary",summary);
		summary.setLayout(null);
		
		responsedata = new JPanel();
		responsedata.setBounds(380, 318, 101, 13);
		viewdatatest.addTab("respone data", null, responsedata, null);
	}
}