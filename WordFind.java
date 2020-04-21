import java.io.*; 
import java.util.*;
//import java.io.File;
//import java.util.Scanner;
//import java.io.FileNotFoundException; 
public class WordFind
{
    public static void main(String args[]) throws FileNotFoundException 
    {
       
       File text=new File(args[0]); 
       Scanner scan=new Scanner(text); 
       String firstLine=scan.next();
       int sizeOfMatrix=(firstLine.length()-1)/2;
       char matrix[][]=new char[sizeOfMatrix][sizeOfMatrix];
       
       for(int row=0;row<sizeOfMatrix;row++)
       {
           
           String result=scan.next();
           scan.next();
           
           for(int column=0,skipDash=1;column<sizeOfMatrix && skipDash<firstLine.length();column++,skipDash+=2)
           {
             
             
               matrix[row][column]=result.charAt(skipDash);
            }       
                
           
           
       }
       scan.close();
       if(args.length==1)
       {
            String ans="yes";
            while(ans.equals("yes")||ans.equals("YES")||ans.equals("Yes"))
            {
                System.out.println("Do you want to search for another Word? Type it");
                Scanner userInput = new Scanner(System.in);
                String variable = userInput.next().toUpperCase();
                
                char[] pattern1=variable.toCharArray();
                FindWord(matrix,pattern1);
                System.out.println("Do you want to search for another Word? If yes type yes,else no");
                
                 ans=userInput.next();
                 
            } //while(ans=="yes"||ans=="YES"||ans=="Yes");
           
        }
        else if (args.length==2)
        {
            File text1=new File(args[1]); 
           Scanner scan1=new Scanner(text1); 
           int numStrings=0;
           while( scan1.hasNext())
           {
               scan1.next();
               numStrings++;
           }
           scan1.close();
           File text2=new File(args[1]); 
           Scanner scan2=new Scanner(text2); 
           String words[]=new String[numStrings];
           for( int i=0;i<numStrings;i++)
           {
               String text3=scan2.next();
               words[i]=text3;
            }
    
        
            for( int i=0;i<words.length;i++)
            {
                String s2=words[i];
                char[] pattern1=s2.toCharArray();
                FindWord ( matrix,pattern1); //two parameters, the grid and the words 
            }
            scan2.close();
           
            
        }
       
       
    }
       
       
    
      
     //here is the word that i will try to see if it exists in the grid 
       
     //String s1="af";
   // char[] pattern=s1.toCharArray();
public static void FindWord (char[][] matrix,char[] pattern)
{
     //char[] pattern=s1.toCharArray();
     int count=0;
     String s1=new String(pattern);
     int numLR=0,numRL=0,numUD=0,numDU=0;
   { 
     int charMatches=0;
     boolean found=false;
     
     
      // from to left to right
     for (int rowIndex=0;rowIndex<matrix.length;rowIndex++)
     {
         for(int columnIndex=0; columnIndex<=matrix.length-pattern.length;columnIndex++)
         {
           charMatches=0;
           numLR++;
           while (charMatches<pattern.length && rowIndex<matrix.length && columnIndex+charMatches<matrix.length && matrix[rowIndex][columnIndex+charMatches]==pattern[charMatches] )
           {
               charMatches++;
               numLR++;
              
           }
           if (charMatches==pattern.length)
           {
               found=true;
               rowIndex=rowIndex+1;
               columnIndex=columnIndex+1;
               System.out.print(s1 +"");
               System.out.println(" "+"was found at row:"+rowIndex +" "+"and column:"+ columnIndex+" "+",L-R"+"("+numLR+")");
            }
            
         } 
         if (!found&&rowIndex==matrix.length-1)
        {
                //System.out.println(s1+" " +"was not found lr");
               count++;
         }       
      } 
      
    }
      // from right to left 
    {
        int charMatches=0;
      boolean found=false;
      for (int rowIndex=0;rowIndex<=matrix.length-1;rowIndex++)
      {
         //for(int columnIndex=matrix.length-1; columnIndex>=matrix.length-pattern.length;columnIndex--)
         for(int columnIndex=matrix.length-1; columnIndex>0;columnIndex--)
       {
           charMatches=0;
           numRL++;
           while (charMatches<pattern.length && columnIndex-charMatches>=0&& rowIndex<matrix.length && matrix[rowIndex][columnIndex-charMatches]==pattern[charMatches] )
           {
               charMatches++;
               numRL++;
            }
           if (charMatches==pattern.length)
           {
                found=true;
               rowIndex=rowIndex+1;
               columnIndex=columnIndex+1;
               System.out.print(s1 +"");
               System.out.println(" "+"was found at row:"+rowIndex +" "+"and column:"+ columnIndex+" "+",R-L"+"("+numRL+")");
               
            }
        } 
        if (!found&&rowIndex==matrix.length-1)
        {
                //System.out.println(s1+" " +"was not found rl");
               count++;
        }    
      }
    }
    
      // from up to down \
    {  
      int charMatches=0;
      boolean found=false;
      for(int column=0;column<matrix.length;column++)
      {
          for (int row=0;row<=matrix.length-pattern.length;row++)
          {
              charMatches=0;
              numUD++;
              while (charMatches<pattern.length && row+charMatches<matrix.length && column<matrix.length&& matrix[row+charMatches][column]==pattern[charMatches])
              {
                  charMatches++;
                  numUD++;
              }
              if (charMatches==pattern.length)
              {
               found=true;
               row=row+1;
               column=column+1;
               System.out.print(s1 +"");
               System.out.println(" "+"was found at row:"+row +" "+"and column:"+ column+" "+",UP-DOWN"+"("+numUD+")");
               
              }
         
          }
          if (!found&&column==matrix.length-1)
           {
                //System.out.println(s1+" " +"was not found upd");
                count++;
           } 
      }
    } 
      //from down to up
    {
      int charMatches=0;
      boolean found=false;
       for(int column=0;column<matrix.length;column++)
      {
          //for (int row=matrix.length-1;row>=matrix.length-pattern.length|;row--)
          for (int row=matrix.length-1;row>=0;row--)
          {
              charMatches=0;
              numDU++;
              while (charMatches<pattern.length && row-charMatches>=0 && column<matrix.length && matrix[row-charMatches][column]==pattern[charMatches])
              {
                  charMatches++;
                  numDU++;
              }
              if (charMatches==pattern.length)
              {
               found=true;
               row=row+1;
               column=column+1;
               System.out.print(s1 +"");
               System.out.println(" "+"was found at row:"+row +" "+"and column:"+ column+" "+",DOWN-UP"+"("+numDU+")");
               
              }
          }
          if (!found&&column==matrix.length-1)
           {
                //System.out.println(s1+" " +"was not found dup");
                count++;
           } 
      }
    }
      
      
     
    if (count==4)
    {
        System.out.println(s1+" "+"was not found"+"("+numLR+numRL+numUD+numDU+")");
    } 
 } 
     
 
}
        
       // diagonally form down to up 
       //upper half
     // { 
         // int charMatches=0;
         // boolean found=false;
         // boolean notFound=false;
         // boolean notFound2=false;
      // for(int column=matrix.length-1;column>=matrix.length-pattern.length;column--)
       // {
            // for (int row=matrix.length-1;row>=matrix.length-pattern.length;row--)
             // {
              // charMatches=0;
              // int row1=0;
              // int column1=0;
              // while (charMatches<pattern.length&& row1<=row && column1<=column && matrix[row-row1][column-column1]==pattern[charMatches])
              // {
                  // charMatches++;
                  // row1++;
                  // column1++;
              // }
              // if (charMatches==pattern.length)
              // {
               // found=true;
               // row=row+1;
               // column=column+1;
               // System.out.print(s1 +"");
               // System.out.println(" "+"was found at row:"+row +" "+"and column:"+ column+" "+",diagonally from up to down, direction L-R");
               
              // }
            // }
            // if (!found&&column==matrix.length-pattern.length)
           // {
                // notFound2=true;
           // }
       // }
       
      // //lower half
        // for(int column=matrix.length-2;column>=matrix.length-pattern.length-1;column--)
        // { 
            // for (int row=matrix.length-1;row>=matrix.length-pattern.length;row--)
            // {
              // charMatches=0;
              // int row1=0;
              // int column1=0;
              // while (charMatches<pattern.length && row-row1>=0 && column-column1>=0 && matrix[row-row1][column-column1]==pattern[charMatches])
              // {
                  // charMatches++;
                  // row1++;
                  // column1++;
              // }
              // if (charMatches==pattern.length)
              // {
               // found=true;
               // row=row+1;
               // column=column+1;
               // System.out.print(s1 +"");
               // System.out.println(" "+"was found at row:"+row +" "+"and column:"+ column+" "+",diagonally from up to down, direction L-R");
               
               
              // }
            // }
            // if (!found&&column==matrix.length-pattern.length)
           // {
                // notFound2=true;
           // }
        // } 
        // if ((notFound==true && notFound2==true)||(notFound==true && notFound2==false)||(notFound==false && notFound2==true))
        // {
             // //System.out.println(s1+" " +"was not found");
             // count++;
        // }
    // }
        
        
    


