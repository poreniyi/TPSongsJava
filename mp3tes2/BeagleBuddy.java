A/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3tes2;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 import java.io.File;
 import java.io.IOException;
 import com.beaglebuddy.mp3.MP3;
 import com.beaglebuddy.id3.enums.Genre;
 import com.beaglebuddy.id3.enums.PictureType;
import java.io.FileInputStream;
import java.io.InputStream;
 import java.util.List;
import com.beaglebuddy.*;
import com.beaglebuddy.id3.enums.v24.FrameType;
import java.util.ArrayList;
import com.beaglebuddy.mp3.MP3BaseID3v23 ;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.Collator;
import java.util.Collections;
import static java.util.Collections.list;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 *
 * @author Niyi Adekunle
 */
public class BeagleBuddy {   
    String Title;
    String Arrangers;// arrangers willbe gotten from get musiy
    String Vocalists;// value will be gotten from the coments from getComents
    String Circle;
    String Album;
    String Performers;
    List<String> ArrangerLines=new ArrayList<>();
       
    List<String> MultipleArrangers=new ArrayList<>();
    List<String> MultipleCircles=new ArrayList<>();
    List<String> MultipleVocalists=new ArrayList<>();
    
    List<String> ArrangersInsert=new ArrayList<>();
    List<String> ArrangersLines=new ArrayList<>();
    List<String> CirclesInsert=new ArrayList<>();
    List<String> CirclesLines=new ArrayList<>();
    List<String> VocalistsInsert=new ArrayList<>();
    List<String> VocalistsLines=new ArrayList<>();
    
    List<String>ArtistSongs=new ArrayList<>();
    
    Map <Integer,String>  ArrangersMap=new HashMap<Integer,String>();//id,ArrangerName
    Map <Integer,String>  VocalistsMap=new HashMap<Integer,String>();//id, vocalistname
    Map <Integer,String>  CirclesMap=new HashMap<Integer,String>();   //id, circlename
    
    
    Map <Integer,Integer>  ArrangerLinesMap=new HashMap<>();   //songid, Arangerid
    Map <Integer,Integer>  VocalLinesMap=new HashMap<>();   //songid, vocalid
    Map <Integer,Integer>  CircleLinesMap=new HashMap<>();   //songid, circleid
    
    
    List <String>  ArrangerLinesList=new ArrayList<>();   //songid, Arangerid
    List <String>  VocalLinesList=new ArrayList<>();   //songid, vocalid
    List <String>  CircleLinesList=new ArrayList<>();   //songid, circleid
  
  
 
      //holds the songs and sources id numbers
   SongsAndSources test =new SongsAndSources();
    
    List<String> FileNames=new ArrayList<>();
    List<String> AllSongInfo=new ArrayList<>();

 
public void getSongsFromDirectory(){
    String placeholder;
    File f=new File("D:\\Documents\\textfiles\\musicfiles");
    //List <File>paths=new ArrayList<>();
    File []paths;
     paths=f.listFiles();
    for(File path:paths){    
        placeholder=path.toString();
        FileNames.add(placeholder);
    }
} 
public void getSongInfo() throws IOException{
    BeagleBuddy poop=new BeagleBuddy();
    try{
        MP3 mp3 = new MP3("d:\\\\documents\\\\textfiles\\\\musicfiles\\\\-Everlasting Happiness-.mp3");

        Title=mp3.getTitle();
        Album=mp3.getAlbum();
        Arrangers=mp3.getMusicBy();// the composer
        Circle=mp3.getBand();     //circle
        Performers=mp3.getLeadPerformer();   
        Vocalists=mp3.getComments();//vocalists
     System.out.println(Arrangers);
     System.out.println(Vocalists);

      if(Vocalists==null){
          System.out.println(Title);
      }

}
  catch(IOException e){
      System.out.println("An error occurred while reading/saving the mp3 file.");
  }
}   
public void getSongInfo2(){
    for(String object:FileNames){
        try{
              MP3 mp3 = new MP3(object); 
        Title=mp3.getTitle();
        Album=mp3.getAlbum();
         Vocalists=mp3.getComments();
         Arrangers=mp3.getMusicBy();
                  Circle= mp3.getBand();
         String circ="";
         String voc="";
         String arr="";        

String []vocalists=Vocalists.split("/");
       for(String vocal: vocalists){
             MultipleVocalists.add(vocal);
             voc+=vocal;
       }
            Arrangers=mp3.getMusicBy();
       String[] arrangers= Arrangers.split("/");
            for(String arranger:arrangers){
                MultipleArrangers.add(arranger);
                arr+=arranger;
            }
            Circle=mp3.getBand();
            if(Circle!=null){
                String []circles= Circle.split("/");
           for(String circle:circles){
               MultipleCircles.add(circle);
               circ+=circle;
            }
          }
          
    AllSongInfo.add("Title:"+Title+" Alubm:"+Album+" Circle:"+circ+" Arrangers:"+arr+" Vocalists:"+voc);
      writeListToTextFile("d:\\documents\\textfiles\\AllSonuuugInfo.txt",AllSongInfo);
  //  System.out.println(AllSongInfo.add("Title:"+Title+" Alubm:"+Album+" Circle:"+circ+" Arrangers:"+arr+" Vocaalists:"+voc));
        }
    catch(IOException e){
        System.out.println("An error occurred while reading/saving the mp3 file.");
        System.out.println(object.toString());
    }
    }
}
/*
name InsertInfo
takes songs from directory and  depending on the parmater
adds either Vocalists,Arrangers,or Circles to the corresponding hashmaps
status:works

*/
    public void InsertInfo(String Name) throws IOException{
    test.songMatch();
    test.sourceMatch();
    test.WordTable();
    test.doword2();
    
    String value="";
    String Performer="";// will hold either arrangers, vocalists, or circles
    int counter=0;
    List<String> ListLines=new ArrayList<>();
    List<String> ListInserts=new ArrayList<>();
     List<String> PerformerLinesList=new ArrayList<>();
  Map <Integer,String>  Map=new HashMap<>();
  Map <Integer,String>  TempMap=new HashMap<>();
  Map <Integer,Integer> LinesMap=new HashMap<>();
    for(String object:FileNames){
        try{
    
              MP3 mp3 = new MP3(object); 
              Title=mp3.getTitle();     
                 switch(Name){
        case "Vocal": value= "Vocalists";
        Performer=mp3.getComments().trim();//get comments AKA THE VOCALISTS  
        break;
        case "Arranger": value= "Arrangers";
        Performer=mp3.getMusicBy().trim();//get comments AKA THE ARRANFERS  
        break;
        case "Circle": value= "Circles";
        Performer=mp3.getBand().trim();//get comments AKA THE CIRCLES 
        break;
    }      
        String []perform;   
        perform=Performer.split("/");     
            for (String perform1 : perform) {
                perform1=perform1.trim();
                  boolean check=true;         
                TempMap.put(counter, perform1.toLowerCase());  
                  for(int id:Map.keySet()){
                        if(perform1.toLowerCase().hashCode()==Map.get(id).toLowerCase().hashCode()){
                          //  System.out.println("check is false"+counter);
                            check=false;
                                   break;
                        }
                    }
                if(!Map.containsValue(perform1)){
                    if(check)
                       Map.put(counter, perform1);
                         counter++;
                }
                
                    int id=0;
    for(int a:Map.keySet()){
        if(Map.get(a).toLowerCase().equals(perform1.toLowerCase())){  
            id=a;
            break;
        }    
    }
    String nos="";
    nos= value;
    nos= value.substring(0,nos.length()-1);
    
                //vocalist inserts for table                   
               ListInserts.add("Insert Into " +value+ " ("+nos+"_id,"+nos+"_Name) values("+id+","+perform1+");");
               //System.out.println("Insert Into " +value+ " ("+nos+"_id,"+nos+"_Name) values("+id+","+perform1+");");
               
               //vocalist lines inserts for vocalist lines table
               //System.out.println("Insert Into VocalistLines (Song_id,Vocalist_Id) values("+Title+perform1);//+Title.hashCode()+","+id+");");  
              // System.out.println("Insert Into VocalistLines (Song_id,Vocalist_Id) values("+Title.hashCode()+","+id+");");
               ListLines.add("Insert Into " +value+"Lines (Song_id,Vocalist_Id) values("+Title.hashCode()+","+id+");");
               int songid=test.getsongid(test.getMap("T"), Title);
               LinesMap.put(songid, id);
               PerformerLinesList.add(+songid+"/"+id);
            }   
            
        }
    catch(IOException e){
        System.out.println("An error occurred while reading/saving the mp3 file.");
    }       
    }
   switch(Name){
                case"Vocal":
                       VocalistsMap.putAll(Map);
                       VocalistsLines.addAll(ListLines);
                       VocalistsInsert.addAll(ListInserts);  
                        VocalLinesList.addAll(PerformerLinesList);
                        break;
                case "Arranger":
                    ArrangersMap.putAll(Map);
                    ArrangersLines.addAll(ListLines);
                    ArrangersInsert.addAll(ListInserts);
                    ArrangerLinesList.addAll(PerformerLinesList);
                        break;
                case"Circle":
                   CirclesMap.putAll(Map);               
                   CirclesLines.addAll(ListLines);
                   CirclesInsert.addAll(ListInserts);
                   CircleLinesList.addAll(PerformerLinesList);
                        break;
            }
}
 
    public void printFileNames(){
    for(int i=0;i<FileNames.size();i++){
        System.out.println(FileNames.get(i));
    }
}
    public void printInfo(){
        for(String stuff: AllSongInfo){
            System.out.println(stuff);
        }
    
}
    public void printInfo2(){
        for(String object:AllSongInfo){
            System.out.println(object);
    }
    }
    /*
    name printInsert
    prints the values in thecorresponding insert list
    the values are in the format Id,Name 
    status:works
    */
    public void printInsert(String valu){
        List<String>Map=new ArrayList<>();
        switch(valu){
            case "C":
                Map.addAll(CirclesInsert);
                break;
            case "V": 
                Map.addAll(VocalistsInsert);
                break;
            case "A":
                Map.addAll(ArrangersInsert);
                break;
        }
        for(String arrang:Map){
            System.out.println(arrang);
        }
    }
    /*
    name printLines
    prints the value sin the corresponding list in the format song,id,performer_id
    status: work in progress 
    */
    public void printLines(String valu){
        List<String>Map=new ArrayList<>();
        switch(valu){
            case "C":
                Map.addAll(CirclesLines);
                break;
            case "V": 
                Map.addAll(VocalistsLines);
                break;
            case "A":
                Map.addAll(ArrangersLines);
                break;
        }
        for(String arrang:Map){
            System.out.println(arrang);
        }
        System.out.println(ArrangersInsert.size());
    }
    /*
    name printHash
    prints the values in the corresponding performerhashmap
    status:works
    */
    public void printHash(String valu){
        /*
        Put A for Arrangers
         V for Vocalists
        C for Circles
        */
        String type="";
        Map <Integer,String>TempMap=new HashMap();
        switch(valu){
            case "A":
                type="Arranger's";
                TempMap.putAll(ArrangersMap);
                break;
            case "V":
                type="Vocalists's";
                TempMap.putAll(VocalistsMap);
             break;
            case "C":
                type="Circle's";
                TempMap.putAll(CirclesMap);
                break;        
    }
        int counter=0;
              List<String> values=new ArrayList<>();
        for(int id:TempMap.keySet()){
           values.add(TempMap.get(id));
        }
   TempMap.clear();
           Collections.sort(values,Collator.getInstance());
           for(String val: values){
               val=val.trim();
               TempMap.put(counter, val.trim());
               //System.out.println(val);
               counter++;
           }
            for(int id:TempMap.keySet()){
           System.out.println("The " + type+ " name is: "+TempMap.get(id)+ " The id is:"+id);
        }
    }
    
    public void printLinesMap(Map<Integer,Integer>MapName){
        int aid=0;
        for(int id:MapName.keySet()){
            aid=MapName.get(id);
            System.out.println("The song id is:"+id+" The performer id is"+aid);
            System.out.println(MapName.size());
        }
    }
    /*
    name getid
    gets the corresponding id of the parameter performnerNAme
    status:works
    */
    public int getid(Map<Integer,String> Hash,String performerName){
        int PerformerId=-1;
        String Name="";
        for(int id:Hash.keySet()){
            Name=Hash.get(id);
            if(performerName.equals(Name)){
                PerformerId=id;
                   break;
            }
        }
                 System.out.println(Hash.get(25));
        return PerformerId;
    }
    public String getArrangerName(int id){
        String artistname="";
    artistname=ArrangersMap.get(id);
        return artistname;
    }
    /*
    creates a view of arrranger/ all songs by that arranger
    status works;
    */
    public void allartistsongs() throws IOException{
        List<String> ArrangersAndTheirSongs=new ArrayList<>();
        String allsongs="";
        int counter=0;
        for(int id: ArrangersMap.keySet()){
            String ArrangerName=ArrangersMap.get(id);
            for(String song:ArrangerLinesList){
                String perform[];
                perform=song.split("/");
                int arrangeid=Integer.parseInt(perform[1]);
                if(id==arrangeid){
                   int songid= Integer.parseInt(perform[0]);
                  String songname=test.getsongName(songid);
                    allsongs+=songname+" ";
                    counter++;
                }                   
            }
             ArrangersAndTheirSongs.add("The Arranger's Name is:"+ArrangerName+" The number of songs is:"+counter+ " Their songs are:"+allsongs);
            allsongs="";
            counter=0;
        }
        writeListToTextFile("d:\\documents\\textfiles\\ArrangersAndTheirSongs.txt",ArrangersAndTheirSongs);
       // test.writeSongs("d:\\documents\\textfiles\\ArrangersAndTheirSongs.txt");
       // Path path=Paths.get()
        for(String line:ArrangersAndTheirSongs){
            System.out.println(line);
        }
        System.out.println(ArrangersAndTheirSongs.size());
      
        System.out.println("The size of ArrangersMap is:"+ArrangersMap.size());
        System.out.println("The counter is:"+counter);
    }
    public void writeListToTextFile(String writeToThis,List<String> list) throws IOException{
       writefiles data=new writefiles(writeToThis,true);
          Path path=Paths.get(writeToThis);//writeToThis;
          Files.deleteIfExists(path);
          for(String object:list){
              data.writeToFile(object);
          }     
        }
    /*
    Creates a view of artists and the source songs they use
    status:work in progress
    */
     public void ArtistAndSource() throws IOException{
        List<String> ArrangersAndTheirSongs=new ArrayList<>();
        HashMap <String,Integer> SongCountMap= new HashMap<>();
        String allsongs="";
        String sourcesong="";
        for(int id: ArrangersMap.keySet()){
            String ArrangerName=ArrangersMap.get(id);
            for(String song:ArrangerLinesList){
                String perform[];
                perform=song.split("/");
                int arrangeid=Integer.parseInt(perform[1]);
                if(id==arrangeid){
                   int songid= Integer.parseInt(perform[0]);
                  String songname=test.getsongName(songid);
                  sourcesong=test.SongToSource(songname);
                    allsongs+=sourcesong+",";
                    if (SongCountMap.get(sourcesong) !=null){ 
                        SongCountMap.put(sourcesong, SongCountMap.get(sourcesong)+1);
                    }
                    else{
                          SongCountMap.put(sourcesong, 1);
                    }
                }                   
            }
              for(String song:SongCountMap.keySet()){
               //System.out.println(song);
            }
           HashMap <String,Integer> SortedMap= sortByValues(SongCountMap);
           SongCountMap.clear();
            String allsongs2="";
            for(String song:SortedMap.keySet()){
                //System.out.println("Number"+SortedMap.get(song)+"The song is"+song);
                allsongs2+="#occurences:"+SortedMap.get(song)+"The song is"+song+"\n";
                //System.out.println("Number of times:"+SortedMap.get(song)+"The song is"+song);
            }
            //System.out.println(allsongs2);
           // ArrangersAndTheirSongs.add("The Arrangers Name is:"+ArrangerName+ " Their songs are:"+allsongs);
          ArrangersAndTheirSongs.add("The Arrangers Name is:"+ArrangerName+ " Their songs are: "
                  + ""+allsongs2);
            allsongs="";
            SortedMap.clear();
            allsongs2="";
        }
        //method is broken
        writeListToTextFile("d:\\documents\\textfiles\\ArrangersAndTheirSources.txt",ArrangersAndTheirSongs);
        System.out.println("The size of the list is:"+ArrangersAndTheirSongs.size());
        System.out.println("Artist and Their Source Complete");
    }
     /*
     did not write this
     */
     public static HashMap sortByValues(HashMap map){
            List list = new LinkedList(map.entrySet());
        Collections.sort(list, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
               return ((Comparable) ((Map.Entry) (o1)).getValue())
                  .compareTo(((Map.Entry) (o2)).getValue());
            }
       });
        HashMap sortedHashMap= new LinkedHashMap();
        for(Iterator it=list.iterator();it.hasNext();){
            Map.Entry entry= (Map.Entry) it.next();
            sortedHashMap.put(entry.getKey(), entry.getValue());
        }
         return sortedHashMap;
     }
  /*
     creates a map of song/arranger name and prints it to a text file
     */
    public void allartongs() throws IOException{
      writefiles data=new writefiles("d:\\documents\\textfiles\\AllArtSongs.txt",true);
     Map<String,String>   ArrangerNamesLineMap=new HashMap<>();
     for(int songid:ArrangerLinesMap.keySet()){
       int arrangerid=ArrangerLinesMap.get(songid);
         ArrangersMap.get((songid));
         ArrangerNamesLineMap.put(test.getsongName(songid), ArrangersMap.get(arrangerid));
     }
     for(String songname:ArrangerNamesLineMap.keySet()){
         System.out.println("The artist is:"+ArrangerNamesLineMap.get(songname)+" The song is:"+songname);
         data.writeToFile("The artist is:"+ArrangerNamesLineMap.get(songname)+" The song is:"+songname);
     }
     System.out.println(ArrangerNamesLineMap.size());
    }
    
}

