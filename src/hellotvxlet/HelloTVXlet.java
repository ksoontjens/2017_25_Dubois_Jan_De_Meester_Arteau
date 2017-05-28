package hellotvxlet;

import java.awt.event.ActionEvent;
import javax.tv.xlet.*;
import org.havi.ui.*;
import org.havi.ui.event.*;
import org.dvb.ui.DVBColor;





public class HelloTVXlet implements Xlet,HActionListener
{
        public HScene scene;
        
        public String playersTurn = "X";
        public HStaticText activePlayer;
        public boolean playerOneActive = true;
        public boolean hideTiedText = false;
        
        public int scoreX =0;
        public int scoreO =0;
        
        public HStaticText scorePlayerX;
        public HStaticText scorePlayerO;
        public HStaticText tiedText;
        
        public HTextButton LB;
        public HTextButton LM;
        public HTextButton LO;
        public HTextButton MB;
        public HTextButton MM;
        public HTextButton MO;
        public HTextButton RB;
        public HTextButton RM;
        public HTextButton RO;
        
        public int[][] field = new int[3][3];
        
        public int turnCounter = 0;
        
        
      
        
        
public HelloTVXlet(){
}


    public void initXlet(XletContext context) throws XletStateChangeException {
      scene = HSceneFactory.getInstance().getDefaultHScene();
      NextGame();
    }

    public void startXlet() throws XletStateChangeException{
        
    }
    public void pauseXlet() {
     
    }

    public void destroyXlet(boolean unconditional) throws XletStateChangeException{
    }
    
    public void actionPerformed(ActionEvent arg0) {
        
        int playerNumber = (playerOneActive)? 1:2;
        
        if(arg0.getActionCommand().equals("LB") && (!(LB.getTextContent(HVisible.NORMAL_STATE)).equals("X") && !(LB.getTextContent(HVisible.NORMAL_STATE)).equals("O")) ) {
            LB.setTextContent(Fill(), HVisible.NORMAL_STATE); 
            field[0][0]= playerNumber;
        }
        if(arg0.getActionCommand().equals("LM") && (!(LM.getTextContent(HVisible.NORMAL_STATE)).equals("X") && !(LM.getTextContent(HVisible.NORMAL_STATE)).equals("O")) ){
            LM.setTextContent(Fill(), HVisible.NORMAL_STATE);
            field[1][0]= playerNumber;
        }
        if(arg0.getActionCommand().equals("LO") && (!(LO.getTextContent(HVisible.NORMAL_STATE)).equals("X") && !(LO.getTextContent(HVisible.NORMAL_STATE)).equals("O")) ){
            LO.setTextContent(Fill(), HVisible.NORMAL_STATE);
            field[2][0]= playerNumber;
        }
        if(arg0.getActionCommand().equals("MB") && (!(MB.getTextContent(HVisible.NORMAL_STATE)).equals("X") && !(MB.getTextContent(HVisible.NORMAL_STATE)).equals("O")) ){
            MB.setTextContent(Fill(), HVisible.NORMAL_STATE);
            field[0][1]= playerNumber;
        }
        if(arg0.getActionCommand().equals("MM") && (!(MM.getTextContent(HVisible.NORMAL_STATE)).equals("X") && !(MM.getTextContent(HVisible.NORMAL_STATE)).equals("O")) ){
            MM.setTextContent(Fill(), HVisible.NORMAL_STATE);
            field[1][1]= playerNumber;
        }
        if(arg0.getActionCommand().equals("MO") && (!(MO.getTextContent(HVisible.NORMAL_STATE)).equals("X") && !(MO.getTextContent(HVisible.NORMAL_STATE)).equals("O")) ){
            MO.setTextContent(Fill(), HVisible.NORMAL_STATE);
            field[2][1]= playerNumber;
        }
        if(arg0.getActionCommand().equals("RB") && (!(RB.getTextContent(HVisible.NORMAL_STATE)).equals("X") && !(RB.getTextContent(HVisible.NORMAL_STATE)).equals("O")) ){
            RB.setTextContent(Fill(), HVisible.NORMAL_STATE);
            field[0][2]= playerNumber;
        }
        if(arg0.getActionCommand().equals("RM") && (!(RM.getTextContent(HVisible.NORMAL_STATE)).equals("X") && !(RM.getTextContent(HVisible.NORMAL_STATE)).equals("O")) ){
            RM.setTextContent(Fill(), HVisible.NORMAL_STATE);
            field[1][2]= playerNumber;
        }
        if(arg0.getActionCommand().equals("RO") && (!(RO.getTextContent(HVisible.NORMAL_STATE)).equals("X") && !(RO.getTextContent(HVisible.NORMAL_STATE)).equals("O")) ){
            RO.setTextContent(Fill(), HVisible.NORMAL_STATE);
            field[2][2]= playerNumber;
        }

        if(HasWon()== 1){
            ++scoreX;
            scorePlayerX.setTextContent("Score X: " + scoreX, HVisible.NORMAL_STATE);
            NextGame();
        }
        else if(HasWon()== 2){
            ++scoreO;
            scorePlayerO.setTextContent("Score O: " + scoreO, HVisible.NORMAL_STATE);
            NextGame();
        }
        else if(HasWon()== 3){
            NextGame();
            tiedText.setTextContent("You have tied!!!!", HVisible.NORMAL_STATE);
            hideTiedText = true;
        }
        scene.repaint();
    }
    public String Fill(){
        String toFill;
        if(hideTiedText == true){
            tiedText.setTextContent("", HVisible.NORMAL_STATE);
            hideTiedText = false;
        }
        if (playerOneActive){
            toFill = "X";
            ++turnCounter;
            playerOneActive = false;
            playersTurn = "O";
        }
        else{ 
            toFill = "O";
            ++turnCounter;
            playerOneActive = true;
            playersTurn = "X";
        }
        activePlayer.setTextContent("player " + playersTurn, HVisible.NORMAL_STATE);
        
        
        
        return toFill;
    }
    private boolean AllTheSame(int vak1, int vak2, int vak3){
        boolean same= false;
        if(vak1 != 0){
        if( vak1 == vak2 && vak2 == vak3 )same = true;
        }
    
    return same;
    }
    
    private int HasWon(){
        int winningPlayer = 0;
        //horizontale
        if(AllTheSame(field[0][0], field[0][1], field[0][2])){
            winningPlayer=(field[0][0]==1)? 1:2;
        }
        if(AllTheSame(field[1][0], field[1][1], field[1][2])){
            winningPlayer=(field[1][0]==1)? 1:2;
        }
        if(AllTheSame(field[2][0], field[2][1], field[2][2])){
            winningPlayer=(field[2][0]==1)? 1:2;
        }
        //verticale
        if(AllTheSame(field[0][0], field[1][0], field[2][0])){
            winningPlayer=(field[0][0]==1)? 1:2;
        }
        if(AllTheSame(field[0][1], field[1][1], field[2][1])){
            winningPlayer=(field[0][1]==1)? 1:2;
        }
        if(AllTheSame(field[0][2], field[1][2], field[2][2])){
            winningPlayer=(field[0][2]==1)? 1:2;
        }
        // diagonalen
        if(AllTheSame(field[0][0], field[1][1], field[2][2])){
            winningPlayer=(field[0][0]==1)? 1:2;
        }
        if(AllTheSame(field[0][2], field[1][1], field[2][0])){
            winningPlayer=(field[0][2]==1)? 1:2;
        }
        if (winningPlayer == 0 && turnCounter==9){
        System.out.println("tie " +turnCounter);
        winningPlayer = 3;
        }
        
        return winningPlayer;
    }
    
    private void NextGame(){
        
        scene.removeAll();
        turnCounter = 0;
        
        for(int i=0, ilen=field.length; i<ilen; ++i){
            
            for(int j=0, jlen=field[i].length; j<jlen; ++j){
                field[i][j]= 0;
            }
        }
        
        
        HStaticText tekst = new HStaticText("Tic Tac Toe",0,0,720,50);
        tekst.setBackgroundMode(HVisible.BACKGROUND_FILL);
        tekst.setBackground(new DVBColor(126,138,162,255));
        scene.add(tekst);
        
        tiedText = new HStaticText("",0,75,720,25);
        tiedText.setForeground(new DVBColor(199,50,38,255));
        scene.add(tiedText);

        activePlayer = new HStaticText("player " + playersTurn,0,75,100,25);
        activePlayer.setForeground(new DVBColor(199,50,38,255));
        scene.add(activePlayer);

        scorePlayerX = new HStaticText("Score X: " + scoreX,10,125,150,25);
        scorePlayerX.setHorizontalAlignment(HVisible.HALIGN_LEFT);
        scorePlayerX.setForeground(new DVBColor(199,50,38,255));
        scene.add(scorePlayerX);

        scorePlayerO = new HStaticText("Score O: " + scoreO,10,155,150,25);
        scorePlayerO.setHorizontalAlignment(HVisible.HALIGN_LEFT);
        scorePlayerO.setForeground(new DVBColor(199,50,38,255));
        scene.add(scorePlayerO);

      LM = new HTextButton("",250,200,100,100);
      LM.setBackgroundMode(HVisible.BACKGROUND_FILL);
      LM.setBackground(new DVBColor(126,138,162,255));
      scene.add(LM);

      MM = new HTextButton("",350,200,100,100);
      MM.setBackgroundMode(HVisible.BACKGROUND_FILL);
      MM.setBackground(new DVBColor(126,138,162,255));
      scene.add(MM);

      LO = new HTextButton("",250,300,100,100);
      LO.setBackgroundMode(HVisible.BACKGROUND_FILL);
      LO.setBackground(new DVBColor(126,138,162,255));
      scene.add(LO);

      MO = new HTextButton("",350,300,100,100);
      MO.setBackgroundMode(HVisible.BACKGROUND_FILL);
      MO.setBackground(new DVBColor(126,138,162,255));
      scene.add(MO);

      LB = new HTextButton("",250,100,100,100);
          LB.setBackgroundMode(HVisible.BACKGROUND_FILL);
          LB.setBackground(new DVBColor(126,138,162,255));
          scene.add(LB);

          MB = new HTextButton("",350,100,100,100);
          MB.setBackgroundMode(HVisible.BACKGROUND_FILL);
          MB.setBackground(new DVBColor(126,138,162,255));
          scene.add(MB);

          RB = new HTextButton("",450,100,100,100);
          RB.setBackgroundMode(HVisible.BACKGROUND_FILL);
          RB.setBackground(new DVBColor(126,138,162,255));
          scene.add(RB);

          RM = new HTextButton("",450,200,100,100);
          RM.setBackgroundMode(HVisible.BACKGROUND_FILL);
          RM.setBackground(new DVBColor(126,138,162,255));
          scene.add(RM);

          RO = new HTextButton("",450,300,100,100);
          RO.setBackgroundMode(HVisible.BACKGROUND_FILL);
          RO.setBackground(new DVBColor(126,138,162,255));
          scene.add(RO);

          // up, down, left, right

          LB.setFocusTraversal(null, LM, null, MB);
          LM.setFocusTraversal(LB, LO, null, MM);
          LO.setFocusTraversal(LM, null, null, MO);
          MB.setFocusTraversal(null, MM, LB, RB);
          MM.setFocusTraversal(MB, MO, LM, RM);
          MO.setFocusTraversal(MM, null, LO, RO);
          RB.setFocusTraversal(null, RM, MB, null);
          RM.setFocusTraversal(RB, RO, MM, null);
          RO.setFocusTraversal(RM, null, MO, null);

          LB.setActionCommand("LB");
          LM.setActionCommand("LM");
          LO.setActionCommand("LO");
          MB.setActionCommand("MB");
          MM.setActionCommand("MM");
          MO.setActionCommand("MO");
          RB.setActionCommand("RB");
          RM.setActionCommand("RM");
          RO.setActionCommand("RO");

          LB.addHActionListener(this);
          LM.addHActionListener(this);
          LO.addHActionListener(this);
          MB.addHActionListener(this);
          MM.addHActionListener(this);
          MO.addHActionListener(this);
          RB.addHActionListener(this);
          RM.addHActionListener(this);
          RO.addHActionListener(this);

          MM.requestFocus();

          HStaticText BG = new HStaticText("",0,0,720,576);
          BG.setBackgroundMode(HVisible.BACKGROUND_FILL);
          BG.setBackground(new DVBColor(38,50,72,255));
          scene.add(BG);
          
          scene.validate(); scene.setVisible(true);
          
    }
    }
    



