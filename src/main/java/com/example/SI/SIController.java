package com.example.SI;

import java.util.ArrayList;
import java.util.List;

import javax.swing.text.html.HTML;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;
import spaceInvaders.*;


@RestController
public class SIController {
    private spaceInvaders si;
    SIController(){
        si = new spaceInvaders();
    }

    @GetMapping(value="/image", produces = MediaType.IMAGE_PNG_VALUE)
    public BufferedImage startGame() {
        BufferedImage newImage = new BufferedImage(400, 400, BufferedImage.TYPE_INT_ARGB);
        Graphics g = newImage.getGraphics();
        si.paint(g);
        return newImage;
    }
    @GetMapping(value="/shoot")
    public String UIshoot() { 
        si.shootButton();
        return Html();
    }
    @GetMapping(value="/left")
    public String UILeft() { 
        si.moveLeft();
        return Html();
    }
    @GetMapping(value="/right")
    public String UIRight() { 
        si.moveRight();
        return Html();
    }
    @GetMapping(value="/html")
    public String Html() {
        StringBuilder htmlBuilder = new StringBuilder();
        htmlBuilder.append("<html>");
        htmlBuilder.append("<head><title>Space Invader</title></head>");
        htmlBuilder.append("<body><p>Enjoy the worst version of Space Invader!</p>");
        htmlBuilder.append("<p>The game is turn based, you don't have to worry about the Aliens tho. They will manage<br> Aliens will move and shoot by themselves on their turn</p>");
        htmlBuilder.append("<p>Press left arrow to move left right arrow to move right and middle button to shoot <br> Keep shooting and you will make it but carefull you have only 3 lives</p>");
        htmlBuilder.append("<div><img src='/image'></img></div>");
        htmlBuilder.append("<div style='display:inline-flex'>");
        htmlBuilder.append("<form action='/left' method='get' style='width:30px'>");
        htmlBuilder.append("<button id='left' type='submit'><span>&#8592;</span></button>");
        htmlBuilder.append("</form>");
        htmlBuilder.append("<form action='/shoot' method='get' style='width:30px'>");
        htmlBuilder.append("<button id='shoot' type='submit'><span>&#9737;</span></button>");
        htmlBuilder.append("</form>");
        htmlBuilder.append("<form action='/right' method='get' style='width:30px'>");
        htmlBuilder.append("<button id='right' type='submit'><span>&#8594;</span></button>");
        htmlBuilder.append("</form>");
        htmlBuilder.append("</div>");
        htmlBuilder.append("</body>");
        htmlBuilder.append("</html>");
        String html = htmlBuilder.toString();

        
       return html;
    }

    }
    /*Temperature temperature = new Temperature();
    List<Temperature> templist = new ArrayList<Temperature>();

    @GetMapping("/temp")
    public Integer getTemp() {
        try {
            return temperature.getTemperature();
        } catch (Exception e) {
            return null;
        } 
    }

    @PostMapping("/list/{value}")    
    public Temperature createTemp(@PathVariable Integer value) {
        Temperature t = new Temperature();
        t.setTemperature(value);
        templist.add(t);
        return t;
    }    
 
    @PostMapping("/list/{value}")    
    public Temperature createTemp(@PathVariable Integer value) {
        Temperature t = new Temperature();
        t.setTemperature(value);
        templist.add(t);
        return t;
    }    

    @PostMapping("/list/temperature")    
    public Temperature createTemp(@RequestParam Integer value) {
        Temperature t = new Temperature();
        t.setTemperature(value);
        templist.add(t);
        return t;
    }    

    @PutMapping("/temp")
    public void putTemp(@RequestParam Integer value) {
        temperature.setTemperature(value);
    }
    

    @PutMapping("/temp")
    public void putTemp(@RequestBody Temperature newTemp) {
        temperature = newTemp;
    }

     
    @GetMapping("/temp")
    public String getTemp() {
        return temperature.getTemperature().toString();
    }

    @GetMapping("/temp")
    public Temperature getTemp() {
        return temperature;
    }
    

    @DeleteMapping("/temp")
    public void deleteTemp() {
        temperature = null;
    }

    @GetMapping("/list/{item}")    
    public String getListItem(@PathVariable String item) {
        return "List for " + item;
    }

    @GetMapping("/list")
    public String[] getList() {
        String[] list = { "Alice", "Bob", "Charlie" };
        return list;
    }*/    


