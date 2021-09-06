package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class HeroService {

    private final HeroRepository heroRepository;

    @Autowired
    public HeroService(HeroRepository heroRepository) {
        this.heroRepository = heroRepository;
    }
    public List<Hero> getHeroes() {
        return heroRepository.findAll();
    }

    public Hero addNewHero(Hero hero) {
        Optional<Hero> studentOptional = heroRepository.findHeroById(hero.getId());
        if(studentOptional.isPresent()) {
            throw new IllegalStateException("email taken");
        }
        return heroRepository.save(hero);
    }

    public Hero findHeroById(Long id) {
        return heroRepository.findHeroById(id).orElseThrow(() -> new UserNotFoundException(id));
    }
    public Hero findHeroByName(String name) {
        return heroRepository.findHeroByName(name).orElseThrow(() -> new UserNotFoundException(name));
    }

    public void deleteHero(Long heroId) throws UserNotFoundException {
        boolean exists = heroRepository.existsById(heroId);
        if(!exists) {
            throw new UserNotFoundException(heroId);
        }
        heroRepository.deleteById(heroId);
    }

    public Hero updateHero(Hero newHero) throws UserNotFoundException{
        return heroRepository.findHeroById(newHero.getId()).map(
                hero -> {
                    hero.setName(newHero.getName());
                    return heroRepository.save(hero);
                }
        ).orElseThrow(() -> new UserNotFoundException(newHero.getId()));
    }
}
