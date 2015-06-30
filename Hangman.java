import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.*;
import java.io.*;
public class Hangman extends JPanel implements ActionListener,KeyListener
{
String reso=""+Hangman.class.getResource("Hangman.class");
String reso2=(((reso.replace("Hangman.class","file.txt")).replace("jar:","")).replace("/","\\")).replace("file:\\","").replace("Hangman.jar!\\","").replace("%20"," ").replace("%5b","[").replace("%5d","]").replace("%7b","{").replace("%7d","}").replace("%3d","=").replace("%5e","^").replace("%23","#").replace("%25","%").replace("%60","`").replace("Hangman.exe!\\","");
static JMenuBar bar=new JMenuBar();
static JMenu file=new JMenu("        File        ");
static JMenu add=new JMenu("        Edit        ");
static JMenuItem exit=new JMenuItem("        Exit        ");
static JMenuItem neu=new JMenuItem("        New        ");
static JMenuItem ad=new JMenuItem("        Add Word       ");
static JMenuItem de=new JMenuItem("        Delete Word       ");
static JFrame window3=new JFrame("Delete");
static JLabel label=new JLabel("Select word to delete");
static JButton del=new JButton("Delete");
static JMenu hel=new JMenu("        Help        ");
static JMenuItem help=new JMenuItem("        Help        ");
static JMenuItem about=new JMenuItem("        About        ");
static JButton canc=new JButton("Cancel");
static JFrame window4=new JFrame(" Help ");
static JPanel hPanel=new JPanel();
static JPanel hPanel2=new JPanel();
static ImageIcon i1=new ImageIcon("22.png");
static ImageIcon i2=new ImageIcon("3.png");
static JComboBox drop=new JComboBox();
static String cur="",before="";
String cword=words();
Timer timer=new Timer(50,this);
Timer timer2=new Timer(165,this);
Timer timer3=new Timer(95,this);
JButton b=new JButton("  Add  ");
JButton g=new JButton("Cancel");
JTextField c=new JTextField(40);
JTextField dx=new JTextField(40);
JLabel e=new JLabel(" Type new word ");
JLabel f=new JLabel(" Retype new word ");
static JFrame window = new JFrame("Window");
JFrame window2 = new JFrame("Add");
int cletterCount=0;
int cletters=cword.length();
int randomNo=cletters/3+1;
int index=(900-((cletters*40)+((cletters-1)*10)))/2;
String randomLetters[]=new String[randomNo+100];
int rlno[]=new int[randomNo+100];
String correctLetters[]=new String[cletters+100];
int correctNo[]=new int[cletters+100];
String wrongLetters[]=new String[26];
int wrongNo[]=new int[26];
String hangman[]=new String[]{"H","A","N","G","M","A","N"};
String win[]=new String[]{"Y","O","U"," ","W","I","N"};
String lose[]=new String[]{"G","A","M","E "," ","O","V","E","R"};
ImageIcon images[]=new ImageIcon[40];
ImageIcon hanger[]=new ImageIcon[7];
ImageIcon cong[]=new ImageIcon[3];
int correctCount=-1,wrongCount=-1,imageCount=-1,imageCheck=0,hangerCheck=3,winnerDecider=0,nos=0,bxx=0,just=0;
boolean decider=false,loser=false,beginning=true,winner=false,imageDecider=false,haveYouLost=false;
boolean d=false,h=false;
String acc="",p="";
static int winX=0,winY=0;
boolean first=true;
public Hangman()
{
try
{
neu.addActionListener(this);
exit.addActionListener(this);
addKeyListener(this);
b.addActionListener(this);
g.addActionListener(this);
setFocusable(true);
ad.addActionListener(this);
about.addActionListener(this);
de.addActionListener(this);
del.addActionListener(this);
canc.addActionListener(this);
help.addActionListener(this);
setFocusTraversalKeysEnabled(false);
hPanel.add(new JLabel(i1));
hPanel.add(new JLabel(i2));
for(int u=0;u<3;u++)
{
cong[u]=new ImageIcon("comp"+(u+2)+".png");
}
for(int h=0;h<40;h++)
{
images[h]=new ImageIcon("h"+(h+11)+".png");
}
for(int h=0;h<7;h++)
{
hanger[h]=new ImageIcon("o"+h+".png");
}
for(int b4=0;b4<randomNo;b4++)
{
randomLetters[b4]="";
}
for(int b4=0;b4<cletters;b4++)
{
correctLetters[b4]="";
}
for(int b4=0;b4<26;b4++)
{
wrongLetters[b4]="";
}
int failed=0;
outer:
for(int b=0;b<randomNo;b++)
{
if(failed==30)
break outer;
int rno=(int)(Math.random()*cletters);
String rs=""+cword.charAt(rno);
for(int b3=0;b3<randomNo;b3++)
{
if(rs.equals(randomLetters[b3]))
{
failed++;
b--;
continue outer;
}
}
inner:
for(int b2=0;b2<cletters;b2++)
{
if(b2==rno)
continue inner;
if(rs.equals(""+cword.charAt(b2)))
{
failed++;
b--;
continue outer;
}
}
rlno[b]=rno;
randomLetters[b]=rs;
cletterCount++;
}
}
catch(Exception e)
{
JOptionPane.showMessageDialog(null," Hangman has encountered a problem and needs to shut down. ");
System.exit(0);
}
}
public void paintComponent(Graphics g)
{
super.paintComponent(g);
setBackground(Color.BLACK);
Graphics2D g2=(Graphics2D)g;
Font font = new Font("Verdana", Font.BOLD, 15);
g2.setStroke(new BasicStroke(4));
g2.setFont(font);
g2.setPaint(Color.WHITE);
int x=(900-((cletters*40)+((cletters-1)*10)))/2;
g2.setStroke(new BasicStroke(2));
for(int p=0;p<=imageCount;p++)
{
images[p].paintIcon(this,g,130,78);
}
if(imageDecider)
{
imageCount++;
images[imageCount].paintIcon(this,g,130,78);
imageDecider=false;
}
if(haveYouLost)
{
hanger[hangerCheck].paintIcon(this,g,130,78);
}
g2.draw(new Line2D.Double(0,570,900,570));
g2.draw(new Line2D.Double(0,78,900,78));
g2.fill(new Rectangle2D.Double(0,78,130,491));
g2.fill(new Rectangle2D.Double(770,78,130,491));
if(wrongCount>=0)
{
beginning=false;
}
for(int a=1;a<=cletters;a++)
{
g2.draw(new Line2D.Double(x,620,x+40,620));
x+=50;
}
if(loser)
{
for(int u=0;u<cletters;u++)
{
g2.drawString(""+cword.charAt(u),((index+u*50)+(index+u*50+40))/2,610);
}
for(int u=0;u<9;u++)
{
g2.drawString(lose[u],((230+u*50)+(230+u*50+40))/2,40);
}
return;
}
for(int c=0;c<randomNo;c++)
{
g2.drawString(randomLetters[c],((index+rlno[c]*50)+(index+rlno[c]*50+40))/2,610);
}
for(int u=0;u<cletters;u++)
{
g2.drawString(correctLetters[u],((index+correctNo[u]*50)+(index+correctNo[u]*50+40))/2,610);
}
if(winner)
{
for(int u=0;u<7;u++)
{
g2.drawString(win[u],((280+u*50)+(280+u*50+40))/2,40);
}
cong[winnerDecider].paintIcon(this,g,130,78);
return;
}
for(int u=0;u<26;u++)
{
g2.drawString(wrongLetters[u].toUpperCase(),((((900-((wrongCount*40)+((wrongCount-1)*10)))/2)+wrongNo[u]*50)+(((900-((wrongCount*40)+((wrongCount-1)*10)))/2)+wrongNo[u]*50+40))/2,40);
}
if(beginning)
{
for(int u=0;u<7;u++)
{
g2.drawString(hangman[u],((280+u*50)+(280+u*50+40))/2,40);
}
}
}
public static void main(String args[])
{
try
{
Hangman obj=new Hangman();
window.setJMenuBar(bar);
bar.add(file);
file.add(neu);
file.add(new JSeparator());
file.add(exit);
bar.add(add);
add.add(ad);
add.add(new JSeparator());
add.add(de);
bar.add(hel);
hel.add(help);
hel.add(new JSeparator());
hel.add(about);
window.add(obj);
window.setSize(900,700);
window.setResizable(false);
Dimension wSize=Toolkit.getDefaultToolkit().getScreenSize();
winX=(wSize.width-window.getSize().width)/2;
winY=(wSize.height-window.getSize().height)/2;
window.setLocation(winX,winY);
window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
window.setVisible(true);
}
catch(Exception e)
{
JOptionPane.showMessageDialog(null," Hangman has encountered a problem and needs to shut down. ");
System.exit(0);
}
}
public void actionPerformed(ActionEvent ef)
{
try
{
if(ef.getSource()==exit)
{
System.exit(0);
}
if(ef.getSource()==neu)
{
cword=words();
cletterCount=0;
cletters=cword.length();
randomNo=cletters/3+1;
index=(900-((cletters*40)+((cletters-1)*10)))/2;
correctCount=-1;
wrongCount=-1;
decider=false;
loser=false;
winner=false;
beginning=true;
imageCheck=0;
imageDecider=false;
imageCount=-1;
timer2.stop();
haveYouLost=false;
winnerDecider=0;
nos=0;
d=false;
h=false;
for(int b4=0;b4<randomNo;b4++)
{
rlno[b4]=0;
}
for(int b4=0;b4<cletters;b4++)
{
correctNo[b4]=0;
}
for(int b4=0;b4<26;b4++)
{
wrongNo[b4]=0;
}
for(int b4=0;b4<randomNo;b4++)
{
randomLetters[b4]="";
}
for(int b4=0;b4<cletters;b4++)
{
correctLetters[b4]="";
}
for(int b4=0;b4<26;b4++)
{
wrongLetters[b4]="";
}
int failed=0;
outer:
for(int b=0;b<randomNo;b++)
{
if(failed==30)
break outer;
int rno=(int)(Math.random()*cletters);
String rs=""+cword.charAt(rno);
for(int b3=0;b3<randomNo;b3++)
{
if(rs.equals(randomLetters[b3]))
{
failed++;
b--;
continue outer;
}
}
inner:
for(int b2=0;b2<cletters;b2++)
{
if(b2==rno)
continue inner;
if(rs.equals(""+cword.charAt(b2)))
{
failed++;
b--;
continue outer;
}
}
rlno[b]=rno;
randomLetters[b]=rs;
cletterCount++;
}
repaint();
}
if(ef.getSource()==timer)
{
imageDecider=true;
imageCheck++;
repaint();
if(imageCheck==4)
{
timer.stop();
imageCheck=0;
}
}
if(ef.getSource()==timer2)
{
haveYouLost=true;
imageDecider=false;
if(hangerCheck==0)
{
h=true;
}
if(h)
{
hangerCheck+=2;
}
if(hangerCheck==6)
{
d=true;
h=false;
}
if(d)
{
hangerCheck-=2;
}
hangerCheck++;
repaint();
}
if(ef.getSource()==timer3)
{
if(winnerDecider==2)
{
timer3.stop();
return;
}
winnerDecider++;
repaint();
}
if(ef.getSource()==ad)
{
window2.setSize(480,170);
window2.setVisible(true);
window.setEnabled(false);
window2.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
window2.setLayout(new FlowLayout());
window2.setLocation(window.getLocation().x+210,window.getLocation().y+250);
window2.add(e);
window2.add(c);
window2.add(f);
window2.add(dx);
window2.add(b);
window2.add(g);
c.setText("");
dx.setText("");
if(loser)
{
timer2.stop();
}
}
else if(ef.getSource()==b)
{
window.setEnabled(true);
window2.dispose();
if(loser)
{
timer2.start();
}
try
{
BufferedReader obj2=new BufferedReader(new FileReader(reso2));
String already=obj2.readLine();
String toBeAdded=c.getText();
int lengthOfWord=toBeAdded.length();
getWords();
String variable="";
int arrayN=0;
String words[]=new String[nos];
for(int h=0;h<bxx;h++)
{
if(acc.charAt(h)==' ')
{
words[arrayN]=variable;
variable="";
arrayN++;
continue;
}
variable+=acc.charAt(h);
}
if(toBeAdded.equals(dx.getText()))
{
if(toBeAdded.equals(""))
{
JOptionPane.showMessageDialog(null,"Please enter a word.");
window2.setVisible(true);
return;
}
for(int test=0;test<lengthOfWord;test++)
{
if(toBeAdded.charAt(test)==' ')
{
JOptionPane.showMessageDialog(null,"Please enter one word only.");
window2.setVisible(true);
return;
}
else if(!(toBeAdded.charAt(test)>='a')||!((toBeAdded.charAt(test)<='z')))
{
JOptionPane.showMessageDialog(null,"Illegal character( "+toBeAdded.charAt(test)+" ). Please try again.");
window2.setVisible(true);
return;
}
}
if(lengthOfWord>18)
{
JOptionPane.showMessageDialog(null,"Word typed is too long( Maximum 18 letters ). Please try again.");
window2.setVisible(true);
return;
}
}
else
{
JOptionPane.showMessageDialog(null,"The words typed are not same. Please retype.");
window2.setVisible(true);
return;
}
for(int h=0;h<nos;h++)
{
if(words[h].equals(toBeAdded))
{
JOptionPane.showMessageDialog(null,"' "+toBeAdded+" ' is already in the dictionary. Please type another word.");
window2.setVisible(true);
return;
}
}
BufferedWriter obj=new BufferedWriter(new FileWriter(reso2));
obj.write(already+toBeAdded+" ");
obj.close();
JOptionPane.showMessageDialog(null,"The word ' "+toBeAdded+" ' has been successfully added");
for(int r=0;r<drop.getItemCount();r++)
{
if(((String)(drop.getItemAt(r))).compareTo(toBeAdded)>0)
{
drop.insertItemAt(toBeAdded,r);
break;
}
}
}
catch(Exception e)
{
JOptionPane.showMessageDialog(null," Hangman has encountered a problem and needs to shut down. ");
System.exit(0);
}
}
else if(ef.getSource()==g)
{
if(loser)
{
timer2.start();
}
window.setEnabled(true);
window2.dispose();
}
if(ef.getSource()==de)
{
if(loser)
{
timer2.stop();
}
if(first)
{
getWords();
just++;
String variable="";
int arrayNo=0;
String words[]=new String[nos];
for(int h=0;h<bxx;h++)
{
if(acc.charAt(h)==' ')
{
words[arrayNo]=variable;
variable="";
arrayNo++;
continue;
}
variable+=acc.charAt(h);
}
java.util.Arrays.sort(words);
drop.removeAllItems();
for(int y=0;y<nos;y++)
{
drop.insertItemAt(words[y],y);
}
first=false;
}
drop.setBackground(Color.WHITE);
Dimension a1=label.getPreferredSize();
Dimension a2=del.getPreferredSize();
Dimension a3=canc.getPreferredSize();
Dimension a4=drop.getPreferredSize();
window3.add(label);
window3.setLocation(window.getLocation().x+210,window.getLocation().y+250);
window3.add(drop);
window3.add(del);
window3.add(canc);
window3.setLayout(null);
window3.setVisible(true);
window3.setSize(480,170);
drop.setBounds(65,48,350,a4.height);
label.setBounds(175,20,a1.width,a1.height);
del.setBounds(163,92,a2.width,a2.height);
canc.setBounds(237,92,a3.width,a3.height);
window3.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
window3.setResizable(false);
window.setEnabled(false);
drop.setSelectedItem(drop.getItemAt(0));
}
else if(ef.getSource()==del)
{
cur=(String)drop.getSelectedItem();
if(loser)
{
timer2.start();
}
p=acc.replace(cur+" ","");
try
{
BufferedWriter obj=new BufferedWriter(new FileWriter(reso2));
obj.write(p);
obj.close();
window.setEnabled(true);
window3.dispose();
JOptionPane.showMessageDialog(null,"The word ' "+cur+" ' has been successfully deleted.");
}
catch(Exception eff)
{
JOptionPane.showMessageDialog(null," Hangman has encountered a problem and needs to shut down. ");
System.exit(0);
}
before=cur;
drop.removeItemAt(drop.getSelectedIndex());
}
else if(ef.getSource()==canc)
{
window.setEnabled(true);
window3.dispose();
if(loser)
{
timer2.start();
}
}
if(ef.getSource()==about)
{
JOptionPane.showMessageDialog(null,"Created by Sourish Banerjee. "+"\n"+"        Achieved with java. ");
}
if(ef.getSource()==help)
{
window4.setVisible(true);
window4.setLayout(null);
window4.getContentPane().setBackground(new Color(244,237,212));
hPanel.setBackground(new Color(244,237,212));
window4.add(hPanel);
window4.setSize(Toolkit.getDefaultToolkit().getScreenSize().width,Toolkit.getDefaultToolkit().getScreenSize().height);
hPanel.setBounds(0,0,Toolkit.getDefaultToolkit().getScreenSize().width,Toolkit.getDefaultToolkit().getScreenSize().height);
window4.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
}
}
catch(Exception eff)
{
JOptionPane.showMessageDialog(null," Hangman has encountered a problem and needs to shut down. ");
System.exit(0);
}
}
public void keyPressed(KeyEvent e)
{
}
public void keyReleased(KeyEvent e)
{
}
public void keyTyped(KeyEvent e)
{
try
{
char letterTyped=e.getKeyChar();
if(cletterCount==cletters||wrongCount==9)
{
Toolkit.getDefaultToolkit().beep();
return;
}
if(letterTyped>='a'&&letterTyped<='z')
{
for(int y=0;y<randomNo;y++)
{
if(randomLetters[y].equals(""+letterTyped))
{
Toolkit.getDefaultToolkit().beep();
return;
}
}
for(int y=0;y<26;y++)
{
if(wrongLetters[y].equals(""+letterTyped))
{
Toolkit.getDefaultToolkit().beep();
return;
}
}
for(int y=0;y<cletters;y++)
{
if(correctLetters[y].equals(""+letterTyped))
{
Toolkit.getDefaultToolkit().beep();
return;
}
}
for(int u=0;u<cletters;u++)
{
if(letterTyped==cword.charAt(u))
{
correctCount++;
cletterCount++;
correctLetters[correctCount]=""+letterTyped;
correctNo[correctCount]=u;
decider=true;
repaint();
}
}
if(decider==false)
{
wrongCount++;
wrongLetters[wrongCount]=""+letterTyped;
wrongNo[wrongCount]=wrongCount;
decider=false;
timer.start();
repaint();
}
else
{
decider=false;
}
}
else
{
Toolkit.getDefaultToolkit().beep();
}
if(wrongCount==9)
{
loser=true;
timer2.start();
repaint();
}
if(cletterCount==cletters)
{
winner=true;
timer3.start();
}
}
catch(Exception ef)
{
JOptionPane.showMessageDialog(null," Hangman has encountered a problem and needs to shut down. ");
System.exit(0);
}
}
public String words()
{
getWords();
String variable="";
int arrayNo=0;
String words[]=new String[nos];
for(int h=0;h<bxx;h++)
{
if(acc.charAt(h)==' ')
{
words[arrayNo]=variable;
variable="";
arrayNo++;
continue;
}
variable+=acc.charAt(h);
}
int no=(int)(Math.random()*(words.length-1));
return(words[no]);
}
public void getWords()
{
bxx=0;
nos=0;
acc="";
try
{
BufferedReader obj=new BufferedReader(new FileReader(reso2));
acc=obj.readLine();
bxx=acc.length();
int arrayNo=0;
for(int h=0;h<bxx;h++)
{
if(acc.charAt(h)==' ')
{
nos++;
}
}
}
catch(Exception e)
{
System.out.println(e);
JOptionPane.showMessageDialog(null," Hangman has encountered a problem and needs to shut down. ");
System.exit(0);
}
}
}