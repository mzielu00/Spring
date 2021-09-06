package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class HeroController {

    private final HeroService heroService;

    @Autowired
    public HeroController(HeroService heroService) {
        this.heroService = heroService;
    }

    @GetMapping("/heroes")
    public ResponseEntity<List<Hero>> getAllHeroes(){
        List<Hero> heroes = heroService.getHeroes();
        return new ResponseEntity<>(heroes, HttpStatus.OK);
    }

    @GetMapping(path = "/heroes/{id}")
    public ResponseEntity<Hero> getHeroId(@PathVariable("id") Long id){
        Hero hero = heroService.findHeroById(id);
        return new ResponseEntity<>(hero, HttpStatus.OK);
    }

    @PostMapping(path = "/add")
    public ResponseEntity<Hero> addHero(@RequestBody Hero hero){
        Hero newHero = heroService.addNewHero(hero);
        return new ResponseEntity<>(newHero, HttpStatus.OK);
    }

    @DeleteMapping(path = "/heroes/{id}")
    public ResponseEntity<Hero> deleteHero(@PathVariable("id") Long heroId) {
        heroService.deleteHero(heroId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(path = "/heroes/{id}")
    public ResponseEntity<Hero> updateHero(@RequestBody Hero hero) {
        try {
            Hero newHero = heroService.updateHero(hero);
            return new ResponseEntity<>(newHero, HttpStatus.OK);
        }catch (UserNotFoundException errorMsg) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
