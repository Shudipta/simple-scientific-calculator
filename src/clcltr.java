import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.*;
public class clcltr extends JFrame implements ActionListener{
    JRadioButton[] rbtn=new JRadioButton[4];
    static JButton[] btn=new JButton[40];
    static int i,k=0,l=0,flag=0,m=0;
    double a[]=new double[5];
    JPanel[] pn=new JPanel[3];
    JTextField tx=new JTextField();
    String st,St[]=new String[105],ss[]=new String[105],Ans="0.0",M="SAKAL PRASHONGSA POROM SRISHTIKARTAR\n",stt;
    static String bn[]={"bin","dec","deg","rad","%","+","-","*",
        "(",")","n!","pi","7","8","9","/",
        "sin(","cos(","tan(","e","4","5","6","Ans",
        "x^y","x^3","x^2","sqrt(","1","2","3","<-",
        "ln(","log(","nCr","nPr","0",".","Enter","C"};
    static String bnn[]={"","","","","%","+","-","*",
        "(",")","!","pi","7","8","9","/",
        "sin(","cos(","tan(","e","4","5","6","Ans",
        "^","^3","^2","sqrt(","1","2","3","",
        "ln(","log(","C","P","0",".","",""};
    public  clcltr(){
        JOptionPane.showMessageDialog(this,M);
        setTitle("JAVA CALCULATOR");St[0]="";
        setVisible(true);
        setLocation(405,251);
        setSize(633,357);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JMenuItem it;
        JMenu fm=new JMenu("File");
        it=new JMenuItem("Quit");
        it.addActionListener(this);
        fm.add(it);
        JMenu em=new JMenu("Help");
        it=new JMenuItem("About");
        it.addActionListener(this);
        em.add(it);
        JMenuBar mb=new JMenuBar();
        setJMenuBar(mb);
        mb.add(fm);mb.add(em);
        BorderLayout bd=new BorderLayout();
        setLayout(bd);
        for(i=0;i<3;pn[i]=new JPanel(),i++);
        add(pn[0],bd.CENTER);
        GridLayout gd=new GridLayout(5,8,5,5);
        pn[0].setLayout(gd);
        add(tx,bd.NORTH);
        tx.setFont(Font.decode("Arial-17"));
        tx.setPreferredSize(new Dimension(50,50));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ButtonGroup[] bg=new ButtonGroup[2];
        for(i=0;i<2;bg[i]=new ButtonGroup(),i++);
        for(i=0;i<4;i++)
        {
            pn[0].add(rbtn[i]=new JRadioButton(bn[i]));
            if((i%2)==0) rbtn[i].setSelected(true);
            if(i<2) bg[0].add(rbtn[i]);
            else bg[1].add(rbtn[i]);
        }
        for( i=4;i<bn.length;i++){
            pn[0].add(btn[i]=new JButton(bn[i]));
            btn[i].setFont(Font.decode("Arial-13"));
            btn[i].addActionListener(this);
        }
    }
public static void main(String[] args){
    new clcltr();
}
    @Override
    public void actionPerformed(ActionEvent ae) {
        stt=ae.getActionCommand();
        if(stt.equals("Quit")){JOptionPane.showMessageDialog(this,"Good Luck");System.exit(0);}
        else if(stt.equals("About")){JOptionPane.showMessageDialog(this,"This programme is copyright to Shudipta Sharma\nID- 1204066\nCse,CUET\n");}
        st=tx.getText();
        for(i=0;i<40;i++){
            if(ae.getSource()==btn[i]){
                tx.setText(st+bnn[i]);
                if(bnn[i]!=""){
                    ss[m++]=bnn[i];
                    char ch=bnn[i].charAt(0);
                    if((ch>=48&&ch<=57)||ch=='.'){
                        St[k]+=ch;
                        flag=1;
                    }
                    else{
                        if(flag==1) {k++;flag=0;}
                        St[k++]=bnn[i];
                        St[k]="";
                    }
                }
            if(ae.getSource()==btn[38])
            {
                if(flag==1) {k++;flag=0;}
                String res=process(); tx.setText(res);
                break;
            }
            if(ae.getSource()==btn[39]){
                tx.setText("");
                k=l=flag=m=0;
                St[k]="";
            }
            if(ae.getSource()==btn[31]){
                st="";
                for(int j=0;j<m-1;j++) st+=ss[j];
                m--;char ch=ss[m].charAt(ss[m].length()-1);
                if((ch>47&&ch<58)||ch=='.')
                    St[k]=St[k].substring(0, St[k].length()-1);
                else
                    St[k]="";
                tx.setText(st);
            }
            if(ae.getSource()==btn[23])
                St[k-1]=ss[m-1]=Ans;
        }
    }
    }
    String process(){;
        int n,j;
        double rd=0,d1=0,res=0;
        for(i=0;i<k;i++){
            if(St[i]=="sin("||St[i]=="cos("||St[i]=="tan("||St[i]=="ln("||St[i]=="log("||St[i]=="sqrt("){
                a[l++]=Double.parseDouble(St[i+1]);
                if(rbtn[2].isSelected()==true)
                    rd=Math.toRadians(a[l-1]);
                if(St[i]=="sin(")
                    res= Math.sin(rd);
                else if(St[i]=="cos(")
                    res=Math.cos(rd);
                else if(St[i]=="tan(")
                    res=Math.tan(rd);
                else if(St[i]=="log(")
                    res=Math.log10(a[l-1]);
                else if(St[i]=="ln(")
                    res=Math.log(a[l-1]);
                else if(St[i]=="sqrt(")
                    res=Math.sqrt(a[l-1]);
            }
            else if(St[i]=="^"){
                a[l++]=Double.parseDouble(St[i-1]);a[l++]=Double.parseDouble(St[i+1]);
                res=Math.pow(a[l-2], a[l-1]);
            }
            else if(St[i]=="^3"){
                a[l++]=Double.parseDouble(St[i-1]);
                res=Math.pow(a[l-1],3);
            }
            else if(St[i]=="^2"){
                a[l++]=Double.parseDouble(St[i-1]);
                res=Math.pow(a[l-1],2);
            }
            else if(St[i]=="!"){
                a[l++]=Double.parseDouble(St[i-1]);
                res=(double)fct((int)a[l-1]);
            }
            else if(St[i]=="C"){
                a[l++]=Double.parseDouble(St[i-1]);a[l++]=Double.parseDouble(St[i+1]);
                res=(double)(fct((int)a[l-2]))/(fct((int)(a[l-2]-a[l-1]))*fct((int)a[l-1]));
            }
            else if(St[i]=="P"){
                a[l++]=Double.parseDouble(St[i-1]);a[l++]=Double.parseDouble(St[i+1]);
                res=(double)(fct((int)a[l-2]))/fct((int)(a[l-2]-a[l-1]));
            }
            else if(St[i]=="%"){
                a[l++]=Double.parseDouble(St[i-3]);a[l++]=Double.parseDouble(St[i-1]);
                res=a[l-2]*a[l-1]/100;
            }
            else if(St[i]=="pi")
                res=2*Math.acos(0);
            else if(St[i]=="e")
                res=Math.pow(13,(1/(Math.log(13))));
            else if(St[i]=="Ans")
                res=Double.parseDouble(St[i]);
            else if(St[i]=="+"||St[i]=="-"||St[i]=="*"||St[i]=="/"){
                a[l++]=Double.parseDouble(St[i-1]);a[l++]=Double.parseDouble(St[i+1]);
                if(St[i]=="+")
                    res=a[l-2]+a[l-1];
                if(St[i]=="-")
                    res=a[l-2]-a[l-1];
                if(St[i]=="*")
                    res=a[l-2]*a[l-1];
                if(St[i]=="/")
                    res=a[l-2]/a[l-1];
            }
        }
        for(i=l-1;i>-1;a[i]=0,i--);
        return Ans=String.valueOf(res);
    }
    long fct(long n){
        if(n==0) return 1;
        for(long j=n-1;j>1;j--) n*=j;
        return n;
    }
}