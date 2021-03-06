package com.cookbook.cookbookv3.SERVICE;

import com.cookbook.cookbookv3.MODEL.Recipe;
import com.cookbook.cookbookv3.REPOSITORY.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeServiceImpl {

    @Autowired
    RecipeRepository rr;

    public void deleteRecipe(Recipe deleted){
        rr.delete(deleted.getName());
    }

    public List<Recipe> findAllRecipe(){
        return rr.findAll();
    }

    public Recipe findByName(String id){
        return rr.findOne(id);
    }

    public Recipe addRecipe(Recipe saved){
        return rr.save(saved);
    }

    public Recipe updateRecipe(String n, String i, String q){
        Recipe r = rr.findOne(n);
        r.addIngredient(i,q);
        rr.save(r);
        return r;

    }

    public Recipe updateRecipeValue(String n, String i, String q){
        Recipe r = rr.findOne(i);
        if(n.equals("time")){
            r.setTime(Integer.parseInt(q));
        }else if(n.equals("persons")){
            r.setPersons(Integer.parseInt(q));
        }
        rr.save(r);
        return r;

    }

    public List<Recipe> filter(String ingredient){
        List<Recipe> ls = this.findAllRecipe();
        for (Recipe r:ls){
            if(r.getIngredients().containsKey(ingredient)){

            }else {
                ls.remove(r);
            }
        }
    return ls;
    }
}
