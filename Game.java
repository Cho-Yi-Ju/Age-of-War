package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class Game implements Initializable{
	
	class Army {
		private ImageView newArmy;
		private double hp;
		private double hp_total;
		private double atk;
		private int classes;
		
		private ProgressBar hp_bar = new ProgressBar();
		private DoubleProperty hp_count;
		
		public Army (double hp , double atk , int classes) {
			this.hp = hp;
			this.hp_total = hp;
			this.atk = atk;
			this.classes = classes;
			
			this.hp_count = new SimpleDoubleProperty(1);
			this.hp_bar.setLayoutX(army_hp.getLayoutX());
			this.hp_bar.setLayoutY(army_hp.getLayoutY());
			this.hp_bar.setPrefHeight(army_hp.getPrefHeight());
			this.hp_bar.setPrefWidth(army_hp.getPrefWidth());
			this.hp_bar.progressProperty().bind(this.hp_count); 
			this.hp_bar.setVisible(true);
			
			if (classes == 1) {
				this.newArmy = new ImageView(army_im1);
			}else if(classes == 2) {
				this.newArmy = new ImageView(army_im2);
			}else if(classes == 3) {
				this.newArmy = new ImageView(army_im3);
			}
			this.newArmy.setFitHeight(army.getFitHeight());
			this.newArmy.setFitWidth(army.getFitWidth());
			this.newArmy.setLayoutX(army.getLayoutX());
			this.newArmy.setLayoutY(army.getLayoutY());
		}
	}
	class Enemy {
		private ImageView newEnemy;
		private double hp;
		private double hp_total;
		private double atk;
		private int classes;
		private ProgressBar hp_bar = new ProgressBar();
		private DoubleProperty hp_count;
		
		public Enemy (double hp , double atk , int classes) {
			this.hp = hp;
			this.hp_total = hp;
			this.atk = atk;
			this.classes = classes;
			
			this.hp_count = new SimpleDoubleProperty(1);
			this.hp_bar.setLayoutX(enemy_hp.getLayoutX());
			this.hp_bar.setLayoutY(enemy_hp.getLayoutY());
			this.hp_bar.setPrefHeight(enemy_hp.getPrefHeight());
			this.hp_bar.setPrefWidth(enemy_hp.getPrefWidth());
			this.hp_bar.progressProperty().bind(this.hp_count); 
			this.hp_bar.setVisible(true);
			
			if (classes == 1) {
				this.newEnemy = new ImageView(enemy_im1);
			}else if(classes == 2) {
				this.newEnemy = new ImageView(enemy_im2);
			}else if(classes == 3) {
				this.newEnemy = new ImageView(enemy_im3);
			}
			
			this.newEnemy.setFitHeight(enemy.getFitHeight());
			this.newEnemy.setFitWidth(enemy.getFitWidth());
			this.newEnemy.setLayoutX(enemy.getLayoutX());
			this.newEnemy.setLayoutY(enemy.getLayoutY());
		}
	}
	
	@FXML
	Timeline fps;
	@FXML
	public Pane field;
	@FXML
	public ImageView castle1;
	@FXML
	public ImageView castle2;
	
	
	@FXML
	public Label coin;
	@FXML
	public Label Ar_coin_1;
	@FXML
	public Label Ar_coin_2;
	@FXML
	public Label Ar_coin_3;
	
	public int myCoin ;
	public int cost1 ;
	public int cost2 ;
	public int cost3 ;
	public int getcoin1 = 20;
	public int getcoin2 = 32;
	public int getcoin3 = 130;
	
	
	
	@FXML
	public ProgressBar progress;
	DoubleProperty buffer_count = new SimpleDoubleProperty(0);
	@FXML
	public Pane buffer1;
	@FXML
	public Pane buffer2;
	@FXML
	public Pane buffer3;
	@FXML
	public Pane buffer4;
	@FXML
	public Pane buffer5;
	
	LinkedList<Army> buffer = new LinkedList<Army>();

	
	@FXML
	public ImageView army;
	@FXML
	public ImageView enemy;
	@FXML
	public ProgressBar army_hp;
	@FXML
	public ProgressBar enemy_hp;
	
	Image castle_im = new Image("file:src\\application\\castle1.png");
	Image encastle_im = new Image("file:src\\application\\encastle1.png");
	
	Image army_im1 = new Image("file:src\\application\\1-1A.png");
	Image army_im2 = new Image("file:src\\application\\1-2A.png");
	Image army_im3 = new Image("file:src\\application\\1-3A.png");
	Image enemy_im1 = new Image("file:src\\application\\1-1E.png");
	Image enemy_im2 = new Image("file:src\\application\\1-2E.png");
	Image enemy_im3 = new Image("file:src\\application\\1-3E.png");

	LinkedList<Army> armys = new LinkedList<Army>();
	LinkedList<Enemy> enemys = new LinkedList<Enemy>();
	
	public double army1_hp = 330;
	public double army2_hp = 330;
	public double army3_hp = 550;
	public double army1_atk = 6;
	public double army2_atk = 3;
	public double army3_atk = 10;
	
	public double enemy1_hp = 300;
	public double enemy2_hp = 300;
	public double enemy3_hp = 500;
	public double enemy1_atk = 6;
	public double enemy2_atk = 3;
	public double enemy3_atk = 10;
	
	
	@FXML
	public Label exp;
	
	public int Exp;
	public int en_Exp;
	public double Army_century ;
	public double Enemy_century;
	
	
	@FXML
	public ProgressBar my_hp;
	@FXML
	public ProgressBar en_hp;
	
	DoubleProperty my_hp_count;
	DoubleProperty en_hp_count;
	public double mycastle_hp_total ;
	public double encastle_hp_total ;
	public double mycastle_hp ;
	public double encastle_hp ;
	
	public boolean Stop = false;
	public boolean Pause = false;
	
	@FXML
	public void win() throws IOException{
		fps.stop();
		Parent win = FXMLLoader.load(getClass().getResource("Win.fxml"));
		Scene winScene = new Scene(win);
		winScene.getRoot().requestFocus();
		Main.currentStage.setScene(winScene);
	}
	@FXML
	public void over() throws IOException{
		fps.stop();
		Parent over = FXMLLoader.load(getClass().getResource("Over.fxml"));
		Scene overScene = new Scene(over);
		overScene.getRoot().requestFocus();  
		Main.currentStage.setScene(overScene);
	}

	@FXML
	public void onBackPressed() {
		fps.stop();
		Main.currentStage.setScene(Main.menuScene);
	}
	
	@FXML
	public void pause_btn() {
		if(Pause == false) {
			fps.pause();
			Pause = true;
		}else {
			fps.play();
			Pause = false;
		}
	}
	
	
	@FXML
	public void set_Army1() {
		if (buffer.size() >= 5) {
			//pass
		}else if(myCoin - cost1 < 0){
			//pass
		}else {
			myCoin -= cost1;
			coin.setText(Integer.toString(myCoin));
			Army setArmy = new Army(army1_hp , army1_atk , 1);
			buffer.add(setArmy);
		}	
	}
	@FXML
	public void set_Army2() {
		if (buffer.size() >= 5) {
			//pass
		}else if(myCoin - cost2 < 0){
			//pass
		}else {
			myCoin -= cost2;
			coin.setText(Integer.toString(myCoin));
			Army setArmy = new Army(army2_hp , army2_atk , 2);
			buffer.add(setArmy);
		}	
	}
	@FXML
	public void set_Army3() {
		if (buffer.size() >= 5) {
			//pass
		}else if(myCoin - cost3 < 0){
			//pass
		}else {
			myCoin -= cost3;
			coin.setText(Integer.toString(myCoin));
			Army setArmy = new Army(army3_hp , army3_atk , 3);
			buffer.add(setArmy);
		}	
	}
	
	@FXML
	public void set_Enemy1() {
		int r1 = (int) (Math.random()*300)+1;
		
		if(en_Exp >= 3000  && Enemy_century == 1) {
			Enemy_century = 2;
			
			enemy_im1 = new Image("file:src\\application\\2-1E.png");
			enemy_im2 = new Image("file:src\\application\\2-2E.png");
			enemy_im3 = new Image("file:src\\application\\2-3E.png");
			
			encastle_im = new Image("file:src\\application\\encastle2.png");
			castle2.setImage(encastle_im);
			
			getcoin1 = 65 ;
			getcoin2 = 98 ;
			getcoin3 = 650 ;
			
			enemy1_hp = 600;
			enemy2_hp = 600;
			enemy3_hp = 850;
			enemy1_atk = 11;
			enemy2_atk = 7;
			enemy3_atk = 15;
			
			encastle_hp_total = encastle_hp_total*2;
			encastle_hp = encastle_hp*2 ;
		}else if(en_Exp >= 12000 && Enemy_century == 2) {
			Enemy_century = 3;
			
			enemy_im1 = new Image("file:src\\application\\3-1E.png");
			enemy_im2 = new Image("file:src\\application\\3-2E.png");
			enemy_im3 = new Image("file:src\\application\\3-3E.png");
			
			encastle_im = new Image("file:src\\application\\encastle3.png");
			castle2.setImage(encastle_im);
			
			getcoin1 = 260 ;
			getcoin2 = 520 ;
			getcoin3 = 1300 ;
			
			enemy1_hp = 1000 ;
			enemy2_hp = 1000 ;
			enemy3_hp = 1500 ;
			enemy1_atk = 16 ; 
			enemy2_atk = 12 ;
			enemy3_atk = 20 ;
			
			encastle_hp_total = encastle_hp_total*2;
			encastle_hp = encastle_hp*2 ;
		}
		
		if(r1 == 1) {
			Enemy setEnemy = new Enemy(enemy1_hp , enemy1_atk, 1);
			enemys.push(setEnemy);
			field.getChildren().add(setEnemy.newEnemy);
			field.getChildren().add(setEnemy.hp_bar);
		}else if(r1 == 2) {
			Enemy setEnemy = new Enemy(enemy2_hp , enemy2_atk , 2);
			enemys.push(setEnemy);
			field.getChildren().add(setEnemy.newEnemy);
			field.getChildren().add(setEnemy.hp_bar);
		}else if(r1 == 3) {
			Enemy setEnemy = new Enemy(enemy3_hp , enemy3_atk , 3);
			enemys.push(setEnemy);
			field.getChildren().add(setEnemy.newEnemy);
			field.getChildren().add(setEnemy.hp_bar);
		}	
		return;
	}
	
	@FXML
	public void next_century() {
		if(Exp >= 3000 && Army_century == 1) {
			Army_century = 2;
			cost1 = 50;
			cost2 = 85;
			cost3 = 500;
			Ar_coin_1.setText("$"+Integer.toString(cost1));
			Ar_coin_2.setText("$"+Integer.toString(cost2));
			Ar_coin_3.setText("$"+Integer.toString(cost3));
			
			army1_hp = 630;
			army2_hp = 630;
			army3_hp = 900;
			army1_atk = 11;
			army2_atk = 7;
			army3_atk = 15;
			army_im1 = new Image("file:src\\application\\2-1A.png");
			army_im2 = new Image("file:src\\application\\2-2A.png");
			army_im3 = new Image("file:src\\application\\2-3A.png");
			
			mycastle_hp_total = mycastle_hp_total*2;
			mycastle_hp = mycastle_hp*2 ;
			
			castle_im = new Image("file:src\\application\\castle2.png");
			castle1.setImage(castle_im);
		}else if(Exp >= 12000 && Army_century == 2) {
			Army_century = 3;
			
			cost1 = 200;
			cost2 = 400;
			cost3 = 1000;
			Ar_coin_1.setText("$"+Integer.toString(cost1));
			Ar_coin_2.setText("$"+Integer.toString(cost2));
			Ar_coin_3.setText("$"+Integer.toString(cost3));
			
			army1_hp = 1100;
			army2_hp = 1100;
			army3_hp = 1550;
			army1_atk = 16;
			army2_atk = 12;
			army3_atk = 20;
			
			army_im1 = new Image("file:src\\application\\3-1A.png");
			army_im2 = new Image("file:src\\application\\3-2A.png");
			army_im3 = new Image("file:src\\application\\3-3A.png");
			
			mycastle_hp_total = mycastle_hp_total*2;
			mycastle_hp = mycastle_hp*2 ;
			
			castle_im = new Image("file:src\\application\\castle3.png");
			castle1.setImage(castle_im);
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		myCoin = Integer.valueOf(coin.getText());
		cost1 = Integer.valueOf(Ar_coin_1.getText().replace("$" , ""));
		cost2 = Integer.valueOf(Ar_coin_2.getText().replace("$" , ""));
		cost3 = Integer.valueOf(Ar_coin_3.getText().replace("$" , ""));
		
		Exp = Integer.valueOf(exp.getText());
		en_Exp = 0;
		Army_century = 1;
		Enemy_century = 1;
		
		mycastle_hp_total = 2000;
		encastle_hp_total = 2000;
		mycastle_hp = 2000;
		encastle_hp = 2000;
		
		my_hp_count = new SimpleDoubleProperty(1);
		en_hp_count = new SimpleDoubleProperty(1);
		my_hp.progressProperty().bind(my_hp_count);
		en_hp.progressProperty().bind(en_hp_count);
		
		fps = new Timeline(new KeyFrame(Duration.millis(5000/60),(e)-> {
			//主時間軸
			set_Enemy1();
			
			ArrayList<Army> tArmys = new ArrayList<Army>(armys);
			ArrayList<Enemy> tEnemys = new ArrayList<Enemy>(enemys);
			//Buffer
			if(buffer.size() > 0) {
				if (buffer.size() == 1) {
					buffer1.setBackground(new Background(new BackgroundFill(Color.RED,null,null)));
					buffer2.setBackground(new Background(new BackgroundFill(Color.GREEN,null,null)));
					buffer3.setBackground(new Background(new BackgroundFill(Color.GREEN,null,null)));
					buffer4.setBackground(new Background(new BackgroundFill(Color.GREEN,null,null)));
					buffer5.setBackground(new Background(new BackgroundFill(Color.GREEN,null,null)));
				}else if (buffer.size() == 2) {
					buffer1.setBackground(new Background(new BackgroundFill(Color.RED,null,null)));
					buffer2.setBackground(new Background(new BackgroundFill(Color.RED,null,null)));
					buffer3.setBackground(new Background(new BackgroundFill(Color.GREEN,null,null)));
					buffer4.setBackground(new Background(new BackgroundFill(Color.GREEN,null,null)));
					buffer5.setBackground(new Background(new BackgroundFill(Color.GREEN,null,null)));
				}else if (buffer.size() == 3) {
					buffer1.setBackground(new Background(new BackgroundFill(Color.RED,null,null)));
					buffer2.setBackground(new Background(new BackgroundFill(Color.RED,null,null)));
					buffer3.setBackground(new Background(new BackgroundFill(Color.RED,null,null)));
					buffer4.setBackground(new Background(new BackgroundFill(Color.GREEN,null,null)));
					buffer5.setBackground(new Background(new BackgroundFill(Color.GREEN,null,null)));
				}else if (buffer.size() == 4) {
					buffer1.setBackground(new Background(new BackgroundFill(Color.RED,null,null)));
					buffer2.setBackground(new Background(new BackgroundFill(Color.RED,null,null)));
					buffer3.setBackground(new Background(new BackgroundFill(Color.RED,null,null)));
					buffer4.setBackground(new Background(new BackgroundFill(Color.RED,null,null)));
					buffer5.setBackground(new Background(new BackgroundFill(Color.GREEN,null,null)));
				}else if (buffer.size() == 5) {
					buffer1.setBackground(new Background(new BackgroundFill(Color.RED,null,null)));
					buffer2.setBackground(new Background(new BackgroundFill(Color.RED,null,null)));
					buffer3.setBackground(new Background(new BackgroundFill(Color.RED,null,null)));
					buffer4.setBackground(new Background(new BackgroundFill(Color.RED,null,null)));
					buffer5.setBackground(new Background(new BackgroundFill(Color.RED,null,null)));
				}
				
				if(buffer_count.get()+0.05 > 1) {
					buffer_count.set(1);
				}else {
					buffer_count.set(buffer_count.get()+0.05);
				}
				
				if (buffer_count.get() >= 1) {
					armys.push(buffer.get(0));
					field.getChildren().add(buffer.get(0).newArmy);
					field.getChildren().add(buffer.get(0).hp_bar);
					
					buffer.remove(0);
					buffer_count.set(0);
				}
				progress.progressProperty().bind(buffer_count);
			}else {
				buffer1.setBackground(new Background(new BackgroundFill(Color.GREEN,null,null)));
			}
			
			//Army移動+戰鬥
			for(int i = tArmys.size()-1 ; i >= 0 ; i--) {
				Army b ;
				b = tArmys.get(i);
				//System.out.println(i);
				if(i < tArmys.size()-1) {
					if(b.newArmy.getLayoutX()+b.newArmy.getFitWidth()+10 > tArmys.get(i+1).newArmy.getLayoutX()) {
						b.newArmy.setLayoutX(b.newArmy.getLayoutX());
						b.hp_bar.setLayoutX(b.hp_bar.getLayoutX());
					}else {
						b.newArmy.setLayoutX(b.newArmy.getLayoutX()+5);
						b.hp_bar.setLayoutX(b.hp_bar.getLayoutX()+5);
					}
				}else if(b.hp <= 0){
					if(b.classes == 1) {
						en_Exp += cost1*2;
					}else if(b.classes == 2) {
						en_Exp += cost2*2;
					}else if(b.classes == 3) {
						en_Exp += cost3*2;
					}
					armys.remove(b);
					field.getChildren().remove(b.newArmy);
					field.getChildren().remove(b.hp_bar);
				}else if(b.classes == 2 &&  (tEnemys.size() > 0 && b.newArmy.getLayoutX() + b.newArmy.getFitWidth() < tEnemys.get(tEnemys.size()-1).newEnemy.getLayoutX() && tEnemys.get(tEnemys.size()-1).newEnemy.getLayoutX() - (b.newArmy.getLayoutX()+b.newArmy.getFitWidth()) <= 400)){
					tEnemys.get(tEnemys.size()-1).hp -= b.atk ;
					
					tEnemys.get(tEnemys.size()-1).hp_count.set(tEnemys.get(tEnemys.size()-1).hp_count.get() - b.atk/tEnemys.get(tEnemys.size()-1).hp_total);
					tEnemys.get(tEnemys.size()-1).hp_bar.progressProperty().bind(tEnemys.get(tEnemys.size()-1).hp_count);
					
					b.newArmy.setLayoutX(b.newArmy.getLayoutX()+5);
					b.hp_bar.setLayoutX(b.hp_bar.getLayoutX()+5);
				}else if(tEnemys.size() > 0 && tArmys.get(tArmys.size()-1).newArmy.getLayoutX()+tArmys.get(tArmys.size()-1).newArmy.getFitWidth() >= tEnemys.get(tEnemys.size()-1).newEnemy.getLayoutX()) {
					//atk
					tArmys.get(tArmys.size()-1).newArmy.setLayoutX(tArmys.get(tArmys.size()-1).newArmy.getLayoutX());
					tEnemys.get(tEnemys.size()-1).newEnemy.setLayoutX(tEnemys.get(tEnemys.size()-1).newEnemy.getLayoutX());
					tArmys.get(tArmys.size()-1).hp_bar.setLayoutX(tArmys.get(tArmys.size()-1).hp_bar.getLayoutX());
					tEnemys.get(tEnemys.size()-1).hp_bar.setLayoutX(tEnemys.get(tEnemys.size()-1).hp_bar.getLayoutX());
					tEnemys.get(tEnemys.size()-1).hp -= tArmys.get(tArmys.size()-1).atk ;
					tArmys.get(tArmys.size()-1).hp -= tEnemys.get(tEnemys.size()-1).atk ;
					
					tEnemys.get(tEnemys.size()-1).hp_count.set(tEnemys.get(tEnemys.size()-1).hp_count.get() - tArmys.get(tArmys.size()-1).atk/tEnemys.get(tEnemys.size()-1).hp_total);
					tArmys.get(tArmys.size()-1).hp_count.set(tArmys.get(tArmys.size()-1).hp_count.get() - tEnemys.get(tEnemys.size()-1).atk/tArmys.get(tArmys.size()-1).hp_total); 
					tEnemys.get(tEnemys.size()-1).hp_bar.progressProperty().bind(tEnemys.get(tEnemys.size()-1).hp_count);
					tArmys.get(tArmys.size()-1).hp_bar.progressProperty().bind(tArmys.get(tArmys.size()-1).hp_count);
					
				}else if(b.newArmy.getLayoutX() + b.newArmy.getFitWidth()+10 > castle2.getLayoutX()) {
					b.newArmy.setLayoutX(b.newArmy.getLayoutX());
					b.hp_bar.setLayoutX(b.hp_bar.getLayoutX());
					if(encastle_hp-b.atk <= 0) {
						encastle_hp = 0;
						en_hp_count.set(0);
						en_hp.progressProperty().bind(en_hp_count);
						Stop = true;
						try {
							win();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}else {
						encastle_hp -= b.atk;
						en_hp_count.set(en_hp_count.get() - b.atk/encastle_hp_total);
						en_hp.progressProperty().bind(en_hp_count);
					}
				}else if(b.classes == 2 && castle2.getLayoutX() - b.newArmy.getLayoutX() + b.newArmy.getFitWidth() <= 400){
					b.newArmy.setLayoutX(b.newArmy.getLayoutX()+5);
					b.hp_bar.setLayoutX(b.hp_bar.getLayoutX()+5);
					if(encastle_hp-b.atk <= 0) {
						encastle_hp = 0;
						en_hp_count.set(0);
						en_hp.progressProperty().bind(en_hp_count);
						Stop = true;
						try {
							win();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}else {
						encastle_hp -= b.atk;
						en_hp_count.set(en_hp_count.get() - b.atk/encastle_hp_total);
						en_hp.progressProperty().bind(en_hp_count);
					}
				}else {
					b.newArmy.setLayoutX(b.newArmy.getLayoutX()+5);
					b.hp_bar.setLayoutX(b.hp_bar.getLayoutX()+5);
				}
			}
			
			//Enemy移動+戰鬥
			for(int i = tEnemys.size()-1 ; i >= 0 ; i--) {
				Enemy b ;
				b = tEnemys.get(i);
				//System.out.println(i);
				if(i < tEnemys.size()-1) {
					if(b.newEnemy.getLayoutX()-10 < tEnemys.get(i+1).newEnemy.getLayoutX()+tEnemys.get(i+1).newEnemy.getFitWidth()) {
						b.newEnemy.setLayoutX(b.newEnemy.getLayoutX());
						b.hp_bar.setLayoutX(b.hp_bar.getLayoutX());
					}else {
						b.newEnemy.setLayoutX(b.newEnemy.getLayoutX()-5);
						b.hp_bar.setLayoutX(b.hp_bar.getLayoutX()-5);
					}
				}else if(b.hp <= 0){
					if(b.classes == 1) {
						myCoin += getcoin1;
						Exp += getcoin1*2;
						exp.setText(Integer.toString(Exp));
						coin.setText(Integer.toString(myCoin));
					}else if(b.classes == 2) {
						myCoin += getcoin2;
						Exp += getcoin2*2;
						exp.setText(Integer.toString(Exp));
						coin.setText(Integer.toString(myCoin));
					}else if(b.classes == 3) {
						myCoin += getcoin3;
						Exp += getcoin3*2;
						exp.setText(Integer.toString(Exp));
						coin.setText(Integer.toString(myCoin));
					}
					enemys.remove(b);
					field.getChildren().remove(b.newEnemy);
					field.getChildren().remove(b.hp_bar);
				}else if( b.classes == 2 && (tArmys.size() > 0 && tArmys.get(tArmys.size()-1).newArmy.getLayoutX()+tArmys.get(tArmys.size()-1).newArmy.getFitWidth() < b.newEnemy.getLayoutX() && b.newEnemy.getLayoutX() - (tArmys.get(tArmys.size()-1).newArmy.getLayoutX()+tArmys.get(tArmys.size()-1).newArmy.getFitWidth()) <= 400)){
					tArmys.get(tArmys.size()-1).hp -= b.atk ;
					
					tArmys.get(tArmys.size()-1).hp_count.set(tArmys.get(tArmys.size()-1).hp_count.get() - b.atk/tArmys.get(tArmys.size()-1).hp_total); 
					tArmys.get(tArmys.size()-1).hp_bar.progressProperty().bind(tArmys.get(tArmys.size()-1).hp_count);
					
					b.newEnemy.setLayoutX(b.newEnemy.getLayoutX()-5);
					b.hp_bar.setLayoutX(b.hp_bar.getLayoutX()-5);

					
				}else if(tArmys.size() > 0 && tArmys.get(tArmys.size()-1).newArmy.getLayoutX()+tArmys.get(tArmys.size()-1).newArmy.getFitWidth() >= tEnemys.get(tEnemys.size()-1).newEnemy.getLayoutX()) {
					//atk
				}else if(b.newEnemy.getLayoutX()-10 < castle1.getLayoutX()+castle1.getFitWidth()) {
					b.newEnemy.setLayoutX(b.newEnemy.getLayoutX());
					b.hp_bar.setLayoutX(b.hp_bar.getLayoutX());
					if(mycastle_hp-b.atk <= 0) {
						mycastle_hp = 0;
						my_hp_count.set(0);
						my_hp.progressProperty().bind(my_hp_count);
						Stop = true;
						try {
							over();
						} catch (IOException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
					}else {
						mycastle_hp -= b.atk;
						my_hp_count.set(my_hp_count.get()-b.atk/mycastle_hp_total);
						my_hp.progressProperty().bind(my_hp_count);
					}
				}else if (b.classes == 2 && b.newEnemy.getLayoutX() - (castle1.getLayoutX()+castle1.getFitWidth()) <= 400){
					b.newEnemy.setLayoutX(b.newEnemy.getLayoutX()-5);
					b.hp_bar.setLayoutX(b.hp_bar.getLayoutX()-5);
					if(mycastle_hp-b.atk <= 0) {
						mycastle_hp = 0;
						my_hp_count.set(0);
						my_hp.progressProperty().bind(my_hp_count);
						Stop = true;
						try {
							over();
						} catch (IOException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
					}else {
						mycastle_hp -= b.atk;
						my_hp_count.set(my_hp_count.get()-b.atk/mycastle_hp_total);
						my_hp.progressProperty().bind(my_hp_count);
					}
				}else {
					b.newEnemy.setLayoutX(b.newEnemy.getLayoutX()-5);
					b.hp_bar.setLayoutX(b.hp_bar.getLayoutX()-5);
				}
			}
			
		}));
		if(Stop) {
			fps.stop();
		}else {
			fps.setCycleCount(Timeline.INDEFINITE);          //這個動作重複無限次
			fps.play();
		}
	}

}
