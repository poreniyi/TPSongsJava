/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3tes2;
 import java.io.File;
 import java.io.IOException;
 import com.beaglebuddy.mp3.MP3;
 import com.beaglebuddy.id3.enums.Genre;
 import com.beaglebuddy.id3.enums.PictureType;
import org.farng.mp3.TagException;

/**
 *
 * @author Niyi Adekunle
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws TagException, IOException {
        
        BeagleBuddy poop=new BeagleBuddy();
        SongsAndSources test=new SongsAndSources();  

 // poop.getSongInfo();
   poop.getSongsFromDirectory();
 //  poop.getSongInfo2();
 
poop.InsertInfo("Arranger");
//System.out.println(poop.getid(poop.ArrangersMap,"_yoc."));
//poop.printHash("A");
//poop.printInsert("A");
//poop.printLines("A");


//poop.printLinesMap(poop.ArrangerLinesMap);

   // poop.allartistsongs();
    
 poop.ArtistAndSource();
 
//poop.allartongs();

//poop.ALines();
//poop.printA();
//poop.tests();
        
    
         
//poop.InsertInfo("Vocal");
//poop.printHash("V");
//poop.printInsert("V");

//poop.InsertInfo("Circle");
//poop.printHash("C");
//poop.printInsert("C");

 
// poop.CircleInsertInfo();
  // poop.printInfo2();
  // poop.printInfo();

   

  //usless stuff below
  
 //test.songMatch();
    //test.sourceMatch();
   //test.WordTable();
  //test.doword2();   
 // test.printMaps("W");
//System.out.println(test.SongToSource("Torn Moon"));
//System.out.println(test.getsongid(test.TouhouSongs,"traumatic"));
  //test.printMaps("T");
  //test.printMaps("S");
    }
    
}
