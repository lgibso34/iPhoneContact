import java.io.* ;

import java.util.*; 

/*
 * this program changes iphone contact names that have the first and last names in the first name field

* then moves the last name to the corrects spot so that the first and last names are in the correct areas
*
*/
public class onefile {

	
	public static void main (String args[]) throws FileNotFoundException{
		
		
		PrintWriter out, out2;
		StringTokenizer stok;
		FileInputStream textFile = null;
		FileInputStream textFile2 = null;
		Scanner inFile = null;
		Scanner inFile2 = null;
		
		String last, first, line, throwaway, output, lineCopy, fileNamee ;
		last = first = line = throwaway = output = lineCopy = fileNamee = "";
		int counter = 0;
		
		
		
		File folder = new File("C:\\Users\\Logan\\OneDrive\\ContactPJ\\contact iPhone"); /*path to your folder*/
	    String[] filesPresent = folder.list(); //makes the files in the folder into a list
	    String [] contents = new String[2000]; //create arrays, length changes depending on the amount of contacts
	    
		
	    if(filesPresent.length==0){
	        System.out.println("Nothing to find");
	    }else{
	        for(String fileName : filesPresent){  // looping through files in the directory
	            if(fileName.toLowerCase().endsWith(".vcf") && (new File(fileName).isFile())){
	            	
	            	fileNamee = fileName.substring(0, fileName.length()-4); //save filename without .vcf
	               
	            	int count = 0; //counters
	            	
	            	
	            	//this is a vcf file.
	                //you can do your operations here
	            	
	            	textFile = new FileInputStream ("C:\\Users\\Logan\\OneDrive\\ContactPJ\\contact iPhone\\" + fileName);
	            	inFile = new Scanner (textFile);
	            	System.out.println("File " + fileName + " has been opened.");
	            	while(inFile.hasNextLine()) //while the file still has more lines keep looping.
	        		{ 
	            		throwaway = inFile.nextLine(); //store line
	        			if(throwaway.contains("N:") && !throwaway.contains("FN:")) //checks whether the line has N; only not FN;
	        			{line = throwaway; //store line that needs the change
	        			lineCopy = line; //copy the line for backup
	        			}
	        			
	        		
	            	       	
	            	if(throwaway.contains("(") || !throwaway.contains(" ")) //if the current line has a parenthese  do not change. if the line has no spaces, do not change
	            	{
	            		counter++;
	            		//System.out.println("Line " + counter + " not changed.");
	            		contents[count] = throwaway; //add this line to the array
	            		count++;
        			}
	            	
	            	
	            	else if (!throwaway.endsWith(";;;")) // if the FN: line is chosen the above IF statement may not apply, this is a final check.
	            		//the line needed will always end in ;;;
	            	{
	            		counter++;
	            		//System.out.println("Line " + counter + " not changed.");
	            		contents[count] = throwaway; //add this line to the array
	            		count++;
	            	}
	            	
	            	
	            	
	            	
	        		else { //when the correct line is chosen....
	        		
	        		stok = new StringTokenizer(line," "); //separates the line into two parts with a space
	        		stok.nextToken();//skip the first token
	        		last = stok.nextToken(); //crab the token that contains the last name
	        		last = last.substring(0, last.length() - 3); //remove the three semi colons from the end of this line to isolate the last name
	        		
	        		//System.out.println(first); //print out the last name
	        		
	        		//System.out.println(lineCopy); //print out the entire line that the last name came from
	        		
	        		StringTokenizer stok2 = new StringTokenizer(lineCopy, ";"); //separate the full line by semi colon
	        		stok2.nextToken(); //skip first token
	        		//stok2.nextToken(); //skip another
	        		
	        		first = stok2.nextToken(); // save first name
	        		first = first.substring(0, first.length()-last.length()-1); // save first name
	        		//System.out.println(last); //show first name
	        		
	        		output = "N:" + last + ";" + first + ";;;"; //write out line with proper values in their spots
	        		//System.out.println(output); //show that line that will be written to the file
	            	
	        		contents[count] = output; //add the modified line the array
	        		count++;
	        			
	        		
	        		} //end else statement
	            	
	            	
	            	
	            	} //end while statement
	            	
	            	
	            	
	            	
	        		
	        		int a=0;
	        			        	    
	        	    out = new PrintWriter("C:\\Users\\Logan\\OneDrive\\ContactPJ\\contact iPhone\\new contacts\\" + fileNamee + " updated.vcf");
	        	    while(contents[a] != null) { //print contents array (updated) to a file with the same name with update at the end.
	            	out.println(contents[a]);
	            	a++;
	        	    }
	                out.close();
	                
	                textFile2 = new FileInputStream ("C:\\Users\\Logan\\OneDrive\\ContactPJ\\contact iPhone\\new contacts\\" + fileNamee + " updated.vcf");
	            	inFile2 = new Scanner (textFile2);
	                /*
	                out2 = new PrintWriter("C:\\Users\\lgibson\\OneDrive\\ContactPJ\\contact iPhone\\new contacts\\" + first + " " + last + ".vcf");
	                while(inFile2.hasNextLine())
	                {
	                	throwaway =inFile2.nextLine();
	                	if(throwaway == "BEGIN:VCARD")
	                	{
	                		while(throwaway != "END:VCARD")
	                		{
	                			out2.println(throwaway);
	                			throwaway = inFile2.nextLine();
	                		}
	                		out2.println(throwaway);
	                		
	                	}
	                	
	                	
	                	
	                	
	                }
	                
	                out2.close();
	        	    */
	        	   
	            	} //end big if to check for .vcf files and that it is a file
	            
	           
	        		
	        }// end of for loop
	        
	        if(counter == 0)
    	    {
            	System.out.println("No vcf files found.");
            }
    	    
    		if(inFile != null)
    		{
    		inFile.close();
    		System.out.println("New file created. \nFile closed.");
    		}
    		
    		System.out.println(counter + " lines searched through.");
	        
	            }//end of first else
	    
	     } //end of main
	  
	}// end class


