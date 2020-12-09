import java.io.*;
public class HandCricket
{
    int user_runs, cpu_runs;

    boolean user_win;

    public BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public HandCricket()
    {
        user_runs = 0;
        cpu_runs = 0;
        user_win = false;
    }

    public void toss() throws IOException
    {
        double toss = Math.random();

        System.out.println("Enter H for heads and T for tails");
        String ch = br.readLine();

        String result = (toss>0.5)? "H":"T";
        String tossr = (result.compareTo("H")==0)? "Heads": "Tails";
        System.out.println("The toss is "+tossr);
        if (result.compareTo(ch)==0)
        {
             System.out.println("You have won the toss. Enter 1 to bat, enter 2 to bowl");
             int choice = Integer.parseInt(br.readLine());
             switch(choice)
             {
                 case 1:
                 bat(1);
                 break;

                 case 2:
                 bowl(1);
                 break;

                 default:
                 System.out.println("Retry toss");
                 toss();
             }
        }
        else
        {
            int choice = (Math.random() > 0.5)? 1:2;

            switch(choice)
            {
                case 1:
                System.out.println("CPU has chosen to bat");
                bowl(1);
                break;

                case 2:
                System.out.println("CPU has chosen to bowl");
                bat(1);
                break;
            }
        }

    
    }

    public void bat(int order) throws IOException
    {
        boolean out = false;
        System.out.println("BATTING TIME");
        while(!out)
        {
            System.out.println("Enter a number from 0 to 6");
            int run = Integer.parseInt(br.readLine());

            if(run >= 7 || run<0)
            {
                System.out.println("Wrong choice. You are out!");
                out = true;
            }

            double CPUh = Math.random() * 6;
            int CPUhand = (int) CPUh;

            System.out.println("CPU chose "+CPUhand);

            if(CPUhand == run)
            {
                out = true;
                System.out.println("OUT!!!!!!!");
                if(order == 1)
                    bowl(2);
          

            }
            else
            {
                user_runs+=run;
                System.out.println("Runs - "+user_runs);
                if(order==2)
                {
                    if(cpu_runs<=user_runs)
                    {
                        user_win = true;
                        out = true;
                    }
                    else
                    {
                        int to_win = cpu_runs - user_runs;
                        System.out.println(to_win+" runs to win");
                    }
                }

            }
        }
    }

    public void bowl(int order) throws IOException
    {
        boolean out = false;
        System.out.println("BOWLING TIME");
        while(!out)
        {
            System.out.println("Enter a number from 0 to 6");
            int ball = Integer.parseInt(br.readLine());

            if(ball >= 7 || ball<0)
            {
                System.out.println("CPU gets free 6 runs. Don't make a mistake again");
                cpu_runs += 6;
            }

            double CPUh = Math.random() * 6;
            int CPUhand = (int) CPUh;

            System.out.println("CPU chose "+CPUhand);

            if(CPUhand == ball)
            {
                out = true;
                System.out.println("OUT!!!!!!!");
                if(order==2)
                    user_win = true;
                else
                    bat(2);

            }
            else
            {
                cpu_runs += CPUhand;
                System.out.println("Runs - "+cpu_runs);
                if(order==2)
                {
                    if(cpu_runs>=user_runs)
                    {
                        user_win = false;
                        out = true;
                    }
                    else
                    {
                        int to_win = user_runs - cpu_runs;
                    
                        System.out.println(to_win+" runs to win");
                    }
                }
            }
        }
    }

    public void winner()
    {
        if(user_win)
        {
            System.out.println("YOU WIN!!!");
        }
        else
        {
            System.out.println("YOU LOSE!!!");
        }
    }

    public static void main(String[] args) throws IOException
    {
        HandCricket obj = new HandCricket();
        obj.toss();
        obj.winner();
    }
}