package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;

import java.awt.FlowLayout;
import java.io.File;
//import java.nio.file.Path;




import javax.swing.JList;

import client.Client;
import models.connection.ModelUpdate;
import models.mapItems.Map;
import models.mapItems.PathUnit;
import models.mapItems.Path;
import models.query.Advice;


import java.util.ArrayList;


public class QueryHistory extends JFrame {

	private JPanel contentPane;
	private InfoPanel infoTextArea; //显示当前选中路径信息
	private JList list;
	private ArrayList<models.query.Query> queryList = Client.getQueriesListByUserName(Client.getUser().getUserName());
	private javax.swing.DefaultListModel<String> model;
//	private ArrayList<Advice> AdviceList;
	MapPanel mapPanel;
	models.query.Query query;

    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
//		queryList = new ArrayList<models.query.Query>();
	    
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QueryHistory frame = new QueryHistory();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public QueryHistory() {
		
		this.setTitle("个人历史记录查询");
		/*
		 * just for test
		 */
		 
		model = new javax.swing.DefaultListModel<String>();     
        for (int i = 0; i < queryList.size(); i++){
        	models.query.Query query = queryList.get(i);
        	assert(query.getResultPath().get(0) != null);
                model.addElement("Query:" + query.getID() + " from " + query.getStartLocationName()
                		+query.getEndLocationName());
                
        }
        
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 660, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(0,50));
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.WEST);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.NORTH);
		
		JPanel panel_3 = new JPanel();
		panel.add(panel_3, BorderLayout.SOUTH);
		
		JLabel lblNewLabel = new JLabel("状态：已登录    用户名：" + Client.getUser().getUserName());
		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lblNewLabel, BorderLayout.EAST);
		
		JPanel panel_4 = new JPanel();
		panel.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel label_5 = new JLabel("查询历史记录");
		label_5.setFont(new Font("宋体", Font.BOLD, 16));
		panel_4.add(label_5);
		
		JPanel panel_5 = new JPanel();
		panel_5.setPreferredSize(new Dimension(0,190));
		contentPane.add(panel_5, BorderLayout.SOUTH);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_6 = new JPanel();
		panel_5.add(panel_6, BorderLayout.WEST);
		
		JPanel panel_7 = new JPanel();
		panel_7.setPreferredSize(new Dimension(0,30));
		panel_5.add(panel_7, BorderLayout.NORTH);
		
		JPanel panel_8 = new JPanel();
		panel_5.add(panel_8, BorderLayout.EAST);
		
		JPanel panel_9 = new JPanel();
		panel_9.setPreferredSize(new Dimension(0,50));
		panel_5.add(panel_9, BorderLayout.SOUTH);
		panel_9.setLayout(new FlowLayout(FlowLayout.CENTER, 60, 10));
		
		JButton button = new JButton("删除");
		button.setFont(new Font("Dialog", Font.BOLD, 14));
		
        button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonActionPerformed(evt);
            }
        });
        panel_9.add(button);
        
		JButton button_2 = new JButton("评价");
		button_2.setFont(new Font("Dialog", Font.BOLD, 14));
        button_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
		panel_9.add(button_2);

     
        
		
		
		JButton button_1 = new JButton("退出");
		button_1.setFont(new Font("宋体", Font.BOLD, 14));
		button_1.addActionListener(new java.awt.event.ActionListener() {
			 public void actionPerformed(java.awt.event.ActionEvent evt) {
	               jButton1ActionPerformed(evt);
	           }
		
	    });
	        
		panel_9.add(button_1);
		
		infoTextArea = new InfoPanel();
		panel_5.add(infoTextArea, BorderLayout.CENTER);
		
		JPanel panel_10 = new JPanel();
		panel_10.setPreferredSize(new Dimension(280,0));
		contentPane.add(panel_10, BorderLayout.WEST);
		panel_10.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_11 = new JPanel();
		panel_10.add(panel_11, BorderLayout.WEST);
		
		JPanel panel_12 = new JPanel();
		panel_12.setPreferredSize(new Dimension(30,0));
		panel_10.add(panel_12, BorderLayout.EAST);
		
	
		
		list = new javax.swing.JList(model);
		panel_10.add(list, BorderLayout.CENTER);
        list.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jlstTaskNamesValueChanged(evt);
            }
        });
        
		JPanel panel_14 = new JPanel();
		contentPane.add(panel_14, BorderLayout.EAST);
		
		JTextArea textArea = new JTextArea();
	
		Map map = Client.getMap();
		mapPanel = new MapPanel(map);
		contentPane.add(mapPanel, BorderLayout.CENTER);
		
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	//	list.setSelectedIndex(0);

	}
	
	/**
	 * 关闭本窗口
	 */
	public void closeFrame() {
		this.setVisible(false);
	}
	
	/*
	 * ActionEvent for button 
	 */
	 private void jButtonActionPerformed(java.awt.event.ActionEvent evt) {                                         
	        // TODO add your handling code here:
	    	 int idx = list.getSelectedIndex();
	         if (idx != -1 && model.getSize() > 0) { 
	             model.remove(idx); 
	             queryList.remove(idx);
	             int cnt = model.getSize();
	             if (cnt != 0) {
	             	list.setSelectedIndex(Math.min(idx, cnt-1));
	             }
	         }
	         else if (model.getSize() <= 0){
	         //	jlblWarning.setVisible(true);
	         }
	         
	         System.out.println("now size is " + queryList.size());
	    }    
	 
	 	/*
		 * ActionEvent for button 
		 */
	    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                                  
	        // TODO add your handling code here:
	    	int m = JOptionPane.showConfirmDialog(null, "您确定要退出历史查看吗？", "消息", JOptionPane.YES_NO_OPTION);
			if(m == JOptionPane.YES_OPTION) {	
				//Client.sendLoginCancleMsg();
				//Client.closeConnection();	
				dispose();					// 关闭登录窗口
			}
	    	
	    }  
	    
		
	    /*
		 * ActionEvent for button 
		 */
	    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                                  
	        // TODO add your handling code here: 
	    	Comment commentFrame = new Comment();
	    	commentFrame.setVisible(true);
	    }  
	    
		/*
		 * ActionEvent for list "鐢ㄦ埛鏌ヨ鍘嗗彶鈥
		 */
	    private void jlstTaskNamesValueChanged(javax.swing.event.ListSelectionEvent evt) {                                           
	        // TODO add your handling code here:
	          if (!evt.getValueIsAdjusting()) {
	                int idx = list.getSelectedIndex();
	                if (idx >= 0 && idx < queryList.size()) {
//	                	models.query.Query query = queryList.get(idx);
	                	query = queryList.get(idx);
	                    /*if (query.getMidLocationName() != null) {
	                    	textArea_1.setText( "Query:" + query.getID() + " from " + query.getStartLocationName()
		                    		+ " by way of " + query.getMidLocationName() + "to " + query.getEndLocationName());  
	                    }
	                    else 
	                    	textArea_1.setText( "Query:" + query.getID() + " from " + query.getStartLocationName()
		                    		+ "to " + query.getEndLocationName());  
	           
	                	textArea_1.setEditable(false);*/
	                	infoTextArea.clear();
	                	infoTextArea.setEditable(false);
	                	infoTextArea.printString(query.toString());
	                	mapPanel.paintPath(query.getResultPath());
	                }
	                else
	                	infoTextArea.clear();
	                
	            }
	    }      
	    
	    
	    public class Comment extends JFrame {

	    	private JPanel contentPane;
	    	private JList list;
	    	private javax.swing.DefaultListModel<String> model;
	    	JTextArea textArea_1;
	    	//JTextArea textArea;
	    	MapPanel mapPanel;
	    	JButton btnNewButton_2;
	    	int grade = -1;
	    	/**
	    	 * Launch the application.
	    	 */
	//    	public static void main(String[] args) {
	    	/*
	    	public void show() {
	    		this.setVisible(true);
	    		
	    		EventQueue.invokeLater(new Runnable() {
	    			public void run() {
	    				try {
	    					Comment frame = new Comment();
	    					frame.setVisible(true);
	    				} catch (Exception e) {
	    					e.printStackTrace();
	    				}
	    			}
	    		});
	    		
	    	}
	    	*/

	    	/**
	    	 * Create the frame.
	    	 */
	    	public Comment() {
	    		
	    		
	    		
	    		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    		setBounds(100, 100, 660, 520);
	    		contentPane = new JPanel();
	    		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	    		setContentPane(contentPane);
	    		contentPane.setLayout(new BorderLayout(0, 0));
	    		
	    		mapPanel = new MapPanel(Client.getMap());
	    		contentPane.add(mapPanel, BorderLayout.CENTER);
	    		
	    		
	    		JPanel panel = new JPanel();
	    		panel.setPreferredSize(new Dimension(0,50));
	    		contentPane.add(panel, BorderLayout.NORTH);
	    		panel.setLayout(new BorderLayout(0, 0));
	    		
	    		JPanel panel_1 = new JPanel();
	    		panel.add(panel_1, BorderLayout.WEST);
	    		
	    		JPanel panel_2 = new JPanel();
	    		panel.add(panel_2, BorderLayout.NORTH);
	    		
	    		JPanel panel_3 = new JPanel();
	    		panel.add(panel_3, BorderLayout.SOUTH);
	    		
	    		JLabel lblNewLabel = new JLabel("\u72B6\u6001\uFF1A\u5DF2\u767B\u5F55   \u7528\u6237\u540D\uFF1Aaaa  ");
	    		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 15));
	    		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
	    		panel.add(lblNewLabel, BorderLayout.EAST);
	    		
	    		JPanel panel_4 = new JPanel();
	    		panel.add(panel_4, BorderLayout.CENTER);
	    		panel_4.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
	    		
	    		JLabel label_5 = new JLabel("  \u53D1\u8868\u8BC4\u4EF7");
	    		label_5.setFont(new Font("宋体", Font.BOLD, 16));
	    		panel_4.add(label_5);
	    		
	    		JPanel panel_5 = new JPanel();
	    		panel_5.setPreferredSize(new Dimension(0,190));
	    		contentPane.add(panel_5, BorderLayout.SOUTH);
	    		panel_5.setLayout(new BorderLayout(0, 0));
	    		
	    		JPanel panel_6 = new JPanel();
	    		panel_5.add(panel_6, BorderLayout.WEST);
	    		
	    		JPanel panel_7 = new JPanel();
	    		panel_7.setPreferredSize(new Dimension(0,30));
	    		panel_5.add(panel_7, BorderLayout.NORTH);
	    		panel_7.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
	    		
	    		JLabel lblNewLabel_1 = new JLabel(" \u8BC4\u8BBA\u5185\u5BB9");
	    		lblNewLabel_1.setFont(new Font("宋体", Font.BOLD, 15));
	    		panel_7.add(lblNewLabel_1);
	    		
	    		JPanel panel_8 = new JPanel();
	    		panel_8.setPreferredSize(new Dimension(140,0));
	    		panel_5.add(panel_8, BorderLayout.EAST);
	    		panel_8.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
	    		
	    		JButton btnNewButton = new JButton("\u6DFB\u52A0\u56FE\u7247");
	    		btnNewButton.setFont(new Font("宋体", Font.BOLD, 14));
	    		
	    		/* Register event for 鈥滄坊鍔犲浘鐗団*/
	    		btnNewButton.addActionListener(new java.awt.event.ActionListener() {
	    			 public void actionPerformed(java.awt.event.ActionEvent evt) {
	    	               newBtnActionPerformed(evt);
	    	           }
	    		
	    	    });
	    		panel_8.add(btnNewButton);
	    		
	    		JButton btnNewButton_1 = new JButton("\u6DFB\u52A0\u89C6\u9891");
	    		btnNewButton_1.setFont(new Font("宋体", Font.BOLD, 14));
	    		
	    		/* Register event for 鈥滄坊鍔犺棰戔 */
	    		btnNewButton_1.addActionListener(new java.awt.event.ActionListener() {
	    			 public void actionPerformed(java.awt.event.ActionEvent evt) {
	    	               newBtnActionPerformed(evt);
	    	           }
	    		
	    	    });
	    		panel_8.add(btnNewButton_1);
	    		
	    		/* Register event for 鈥滆瘎浠锋墦鍒嗏*/
	    		btnNewButton_2 = new JButton("评价");
	    		btnNewButton_2.setFont(new Font("宋体", Font.BOLD, 14));
	    		btnNewButton_2.addActionListener(new java.awt.event.ActionListener() {
	    			 public void actionPerformed(java.awt.event.ActionEvent evt) {
	    	               newBtn2ActionPerformed(evt);
	    	           }
	    		
	    	    });
	    		panel_8.add(btnNewButton_2);
	    		
	    		JPanel panel_9 = new JPanel();
	    		panel_9.setPreferredSize(new Dimension(0,50));
	    		panel_5.add(panel_9, BorderLayout.SOUTH);
	    		panel_9.setLayout(new FlowLayout(FlowLayout.CENTER, 60, 10));
	    		
	    		JButton button = new JButton("\u786E\u8BA4");
	    		button.setFont(new Font("锟斤拷锟斤拷", Font.BOLD, 14));
	    		
	    		/* Register eventListener for 鈥滅‘璁も */
	    		button.addActionListener(new java.awt.event.ActionListener() {
	    			 public void actionPerformed(java.awt.event.ActionEvent evt) {
	    	               buttonActionPerformed(evt);
	    	               
	    	           }
	    		
	    	    });
	    		panel_9.add(button);
	    		
	    		JButton button_1 = new JButton("退出");
	    		button_1.setFont(new Font("锟斤拷锟斤拷", Font.BOLD, 14));
	    		
	    		/* Register eventListener for  */
	    		button_1.addActionListener(new java.awt.event.ActionListener() {
	    			 public void actionPerformed(java.awt.event.ActionEvent evt) {
	    	               button_1ActionPerformed(evt);
	    	           }
	    		
	    	    });
	    		
	    		panel_9.add(button_1);
	    		
	    		textArea_1 = new JTextArea();
	    		panel_5.add(textArea_1, BorderLayout.CENTER);
	    		
	    		JPanel panel_10 = new JPanel();
	    		panel_10.setPreferredSize(new Dimension(280,0));
	    		contentPane.add(panel_10, BorderLayout.WEST);
	    		panel_10.setLayout(new BorderLayout(0, 0));
	    		
	    		JPanel panel_11 = new JPanel();
	    		panel_10.add(panel_11, BorderLayout.WEST);
	    		
	    		JPanel panel_12 = new JPanel();
	    		panel_12.setPreferredSize(new Dimension(30,0));
	    		panel_10.add(panel_12, BorderLayout.EAST);
	    		
	    		/* just for test */
	    		
//	    		models.query.Query query = QueryHistory.this.queryList.get(QueryHistory.this.list.getSelectedIndex());
	    		models.query.Query query = QueryHistory.this.query;
	    		//	AdviceList = new ArrayList<Advice>(query.get);
	    	//	for(int i = 0; i < query.getResultPath().size(); i++)
	    		//	 AdviceList.add(new Advice(i));
	    		model = new javax.swing.DefaultListModel<String>();   
	    		ArrayList<models.mapItems.Path> pathList = query.getResultPath();
	    		System.out.println(pathList.size());
	            for (Path path : pathList){
                	//System.out.println(path == null);
                	System.out.println("name is " + path.getName());
	            	model.addElement(path.getName());
	            	ArrayList<models.mapItems.PathUnit> pathUnitList = path.getPathUnitList();
	            	for (int j = 0; j < pathUnitList.size(); j++)
	                    model.addElement("    " + (pathUnitList.get(j)).getName());
	            }
	            
	            list = new JList(model);
	    		panel_10.add(list, BorderLayout.CENTER);
	    		
	    	    list.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
	    	    list.addListSelectionListener(new javax.swing.event.ListSelectionListener() { 
	    	    	public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
	    	    		jlstTaskNamesValueChanged(evt);
	    	                
	    	        }
	    	    });
	    	    list.setSelectedIndex(0);
	    		
	    		JPanel panel_14 = new JPanel();
	    		contentPane.add(panel_14, BorderLayout.EAST);
	    		
	    		//textArea = new JTextArea();
	    		//contentPane.add(textArea, BorderLayout.CENTER);
	    		
	    	}
	    	
	    	
	    	/*
	    	 * ActionEvent for "娣诲姞鍥剧墖鈥濆強鈥滄坊鍔犺棰戔
	    	 * to be continued
	    	 */
	    	private void  newBtnActionPerformed(java.awt.event.ActionEvent evt) {                                         
	            // TODO add your handling code here:
	        	JFileChooser fileChooser = new JFileChooser();
	        	int returnVal = fileChooser.showOpenDialog(contentPane);
	        	 if (returnVal == JFileChooser.APPROVE_OPTION) {
	                 File file = fileChooser.getSelectedFile();
	                 //This is where a real application would open the file.
	                 System.out.println("Opening: " + file.getName() + "." + "\n");
	             } else {
	            	 System.out.println("Open command cancelled by user." + "\n");
	             }
	        }  
	    	
	    	/*
	    	 * ActionEvent for "璇勪环鎵撳垎鈥
	    	 * to be continued
	    	 */
	    	private void  newBtn2ActionPerformed(java.awt.event.ActionEvent evt) {                                         
	            // TODO add your handling code here:
	    		Object[] possibilities = {"1", "2", "3","4","5"};
	    		grade = Integer.parseInt((String)JOptionPane.showInputDialog(
	    		                    null,
	    		                    "请选择分数",    		                
	    		                    "Customized Dialog",
	    		                    JOptionPane.PLAIN_MESSAGE,
	    		                    null,
	    		                    possibilities,
	    		                    "1"));
	    		
	        }  
	    	
	    	/*
	    	 * ActionEvent for button "
	    	 */
	    	private void  buttonActionPerformed(java.awt.event.ActionEvent evt) {                                         
	            // TODO add your handling code here:
	    		//提示用户是否确定提交
	    		int m = JOptionPane.showConfirmDialog(null, "您确定要提交评论吗？", "消息", JOptionPane.YES_NO_OPTION);
				if(m == JOptionPane.NO_OPTION) {
					return;
				}
	    		
	    		
	            int idx = list.getSelectedIndex();
	            ArrayList<Path> pathList = query.getResultPath();
	            
	            String adviceText = textArea_1.getText().trim();	            
	            
	            if (grade <= 0) {
	            	return;
	            }
	            
	            int curIndex = 0;
	            for (int i = 0;i < pathList.size();i ++) {
	            	Path path = pathList.get(i);
	            	if (curIndex == idx) {
	            		for (PathUnit p : path.getPathUnitList()) {
	            			Client.updateGradeByPathUnitID(p.getID(), grade);
	            		}
	            		return;
	            	}
	            	curIndex ++;
	            	
	            	for (PathUnit pathUnit : path.getPathUnitList()) {
	            		if (curIndex == idx) {
	            			Client.updateGradeByPathUnitID(pathUnit.getID(), grade);
	            			if (!adviceText.equals("")) {
	            				Client.insertNewTextComment(Client.getUser().getUserName(), pathUnit.getID(), adviceText);
	            			}
	            			return;
	            		}
	            		curIndex ++;
	            	}
	            }
	            
	            /*if(model.getElementAt(idx).startsWith("    ")) {
	            	//path unit
	            	  if (textArea_1.getText() != null) {
	  	 //           	PathUnit pathUnit = 
	  	 //           	Advice advice = new Advice(Client.getUser(), pathUnitID, text)
	  	            }
	            }
	            else {
	            	//path
	            }
	          
	            if (idx >= 0 && idx < AdviceList.size() && textArea_1.getText() != null
	            		&& AdviceList.get(idx).getContent() == null) {
	            	AdviceList.get(idx).addContent(new AdviceContent(textArea_1.getText()));
	            	System.out.println(AdviceList.get(idx).getContent().getText());
	            	//textArea_1.setEditable(false);
	            	textArea_1.setText(null);
	            }*/
	            
	        }   
	    	
	    	/*
	    	 * ActionEvent for button "
	    	 * to be continued
	    	 */
	        private void  button_1ActionPerformed(java.awt.event.ActionEvent evt) {                                                  
	            // TODO add your handling code here: 
	        	dispose();
	        }  
	        
	        /*
	    	 * ActionEvent for list: 鐢ㄦ埛鍒楄〃
	    	 */
	        private void jlstTaskNamesValueChanged(javax.swing.event.ListSelectionEvent evt) {                                           
	            // TODO add your handling code here:
	              if (!evt.getValueIsAdjusting()) {
	                    int idx = list.getSelectedIndex();
	                    ArrayList<Path> pathList = query.getResultPath();	    	            
	    	            String adviceText = textArea_1.getText().trim();
	                    
	                    int curIndex = 0;
	    	            for (int i = 0;i < pathList.size();i ++) {
	    	            	Path path = pathList.get(i);
	    	            	if (curIndex == idx) {
	    	            		textArea_1.setText("");
	    	            		textArea_1.setEditable(false);
	    	            		ArrayList<Path> temp = new ArrayList<Path>();
	    	            		temp.add(path);
	    	            		mapPanel.paintPath(temp);
	    	            	}
	    	            	curIndex ++;
	    	            	
	    	            	for (PathUnit pathUnit : path.getPathUnitList()) {
	    	            		if (curIndex == idx) {
	    	            			//pathUnit
	    	                    	textArea_1.setText("");
	                        		textArea_1.setEditable(true);
	                        		textArea_1.requestFocus();
	                        		mapPanel.paintPathUnit(pathUnit);
	    	            			return;
	    	            		}
	    	            		curIndex ++;
	    	            	}
	    	            }
	                    /*
	                    if (idx >= 0 && idx < AdviceList.size()) {
	                    	if (AdviceList.get(idx).getContent() == null) {
	                    		textArea_1.setText(null);
	                    		textArea_1.setEditable(true);
	                    		textArea_1.requestFocus();
	                    	}
	                    	else {
	                    		textArea_1.setText(AdviceList.get(idx).getContent().getText());
	                    		textArea_1.setEditable(false);	
	                    	}
	                    }
	                    */
	              }
	        }

	    }
}
