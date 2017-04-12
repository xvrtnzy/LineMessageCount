import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import javax.swing.JProgressBar;
import javax.swing.JTextArea;

public class testController {
	private File getfile;
	private String header = "";
	
	public void count(){
		testView2 displayframe = new testView2();
		JTextArea display = displayframe.getTextArea();
		//JProgressBar percent = displayframe.getProgressBar();
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(this.getfile), "UTF-8"));
			BufferedWriter bw = new BufferedWriter(new FileWriter("D:\\testLine.txt", false));
			Pattern pdate = Pattern.compile("([0-9]{4})[./]{1}([0-9]{1,2})[./]{1}([0-9]{1,2})");
			Pattern ptime = Pattern.compile("([0-9]{1,2})[:]{1}([0-5][0-9])\t(.)*");
			String firstday = "";
			String lastday = "";
			String day = "";
			int days = 0;
			int total = 0;
			String line;
			if(br.ready()) {
				while((line = br.readLine()) != null){
					if(line.length() > 0) {
						if(line.charAt(0) == 65279){ ////處理讀取BOM（ byte-order mark )格式檔案,在讀取utf8檔案的開頭會有utf8檔案格式的標記,需略過此標記再重串內容,標記16進位EF BB BF
							line = line.substring(1);	
							header += line;
							header += "\r\n";
							continue;
						}
						header += line;
						int temp = line.indexOf('：',0);
						lastday = line.substring(temp+1, temp+11);						
					} else {
						break;
					}
				}
				
				boolean firsttime = true;
				while((line = br.readLine()) != null){
					//bw.write(line);
					//bw.write("\r\n");
					//System.out.println(line);
					int count = 0;
					Matcher md = pdate.matcher(line);
					if(md.lookingAt()){
						day = line;
						days++;	
						if(firsttime){
							firstday = line.substring(0, line.indexOf("("));
							firsttime = false;
						}
						while((line = br.readLine()) != null){
							if(line.length() > 0){
								Matcher mt = ptime.matcher(line);
								//Matcher md2 = pdate.matcher(line);
								if(mt.lookingAt()){
									count++;
								}
							} else {
								total += count;
								break;
							}
						}
						//System.out.println(String.format("%s: %3d 則", day, count));
						display.append(String.format("%s: %3d 則%n", day, count));
					}		
				}
				display.append(String.format("%n%s%n", header));
				display.append(String.format("統計自 %s 到 %s 共: %2d天 ，總共: %4d則訊息", firstday, lastday, days, total));
				//System.out.print("總共: "+ days +"天");
				//System.out.print(" ,共: "+ total +" 則訊息");
			}
			header = "";
			bw.close();
			br.close();
		} catch (FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void acceptFile(File src){
		this.getfile = src;
	}
	
	public boolean isgetFile(){
		if(this.getfile == null){
			return false;
		} else {
			return true;
		}
	}
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
