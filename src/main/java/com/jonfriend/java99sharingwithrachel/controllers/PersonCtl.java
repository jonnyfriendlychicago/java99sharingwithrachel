package com.jonfriend.java99sharingwithrachel.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jonfriend.java99sharingwithrachel.models.Book;
import com.jonfriend.java99sharingwithrachel.models.ExpenseMdl;
import com.jonfriend.java99sharingwithrachel.models.PersonMdl;
import com.jonfriend.java99sharingwithrachel.services.PersonSrv;

@Controller
public class PersonCtl {
	
	@Autowired
	PersonSrv personSrv; 
	
//	@RequestMapping("/expenses/{expenseId}") 
//	public String expenseView(Model model, @PathVariable("expenseId") Long expenseId) {
//		ExpenseMdl expenseMdl = expenseSrv.findExpense(expenseId); 
//		model.addAttribute("expense", expenseMdl);  
//		return "expenseprofile.jsp"; 
//	}

	
	// above given by platform, below BP'ed from ExpenseCtl
	
	// display list of all records
	@RequestMapping("/person")
	public String displayAll(Model model) {
		List<PersonMdl> listy = personSrv.returnAll();
		model.addAttribute("personList", listy); 
		return "person/personlist.jsp"; 
	}
	
	// view one record
	@GetMapping("/person/{personId}")
	public String displayOne(Model model, @PathVariable("personId") Long personId ) {
		PersonMdl intVar = personSrv.findById(personId);
		model.addAttribute("person", intVar);
		return "person/person.jsp";
	}
	
	// new record: initiate it!
	@RequestMapping("/person/new")
	public String initiateNewOne( @ModelAttribute("person") PersonMdl personMdl ) {
		return "person/personnew.jsp";
	}
	
	// new record: finalize/save it (or get kicked back b/c errors)
	@PostMapping("/person/new") 
	public String processNewOne(
		@Valid 
		@ModelAttribute("person") PersonMdl personMdl 
		, BindingResult result
	) {
		
		if (result.hasErrors()) {
            return "person/personnew.jsp";
        } else {
        	personSrv.createNew(personMdl);
            return "redirect:/person";
        }
	}
	
	// edit record: initiate it!
	@GetMapping("/person/{personId}/edit")
	public String editOne(@PathVariable("personId") Long personId, Model model) {
		PersonMdl intVar = personSrv.findById(personId); 
		model.addAttribute("person", intVar);  
		return "person/personedit.jsp";
	}
	
	// edit record: finalize/save it (or get kicked back b/c errors)
	@PostMapping("/person/{personId}/edit")
	public String update(
			@Valid 
			@ModelAttribute("person") PersonMdl personMdl 
			, BindingResult result) {
		if (result.hasErrors()) {
			return "person/personedit.jsp";
		} else {
			personSrv.update(personMdl);
			return "redirect:/person";
		}
	}
	
    @DeleteMapping("/person/{personId}")
    public String deleteOne(@PathVariable("personId") Long personId) {
    	personSrv.delete(personId);
        return "redirect:/person";
    }
    
//	below are the methods for unified "list-plus-create/edit"... 
	
    // display list of all records, with create form
	@RequestMapping("/personplus")
	public String personpluscreate(@ModelAttribute("person") PersonMdl personMdl , Model model) {
		List<PersonMdl> intVar = personSrv.returnAll();
		model.addAttribute("personList", intVar); 
		return "personplus/personPlus.jsp"; 
	}
	
	// display list, then process the new 
	@PostMapping("/personpluscreate") 
	public String personpluscreate(
		@Valid 
		@ModelAttribute("person") PersonMdl personMdl 
		, BindingResult result
		, Model model
		
	) {
		if (result.hasErrors()) { 
            List<PersonMdl> intVar = personSrv.returnAll();
    		model.addAttribute("personList", intVar);
//    		return "personlistcreate.jsp";
    		return "personplus/personPlus.jsp";
    		
        } else {
        	personSrv.createNew(personMdl);
            return "redirect:/personplus";
        }
	}
	
	// display list while editting a record
	// edit record: initiate it!
	@RequestMapping("/personpluscreate/{personId}/edit")
	public String displayAllEditOne(@PathVariable("personId") Long personId, Model model) {
		
		List<PersonMdl> intVar2 = personSrv.returnAll();
		model.addAttribute("personList", intVar2); 
		
		PersonMdl intVar = personSrv.findById(personId); 
		model.addAttribute("person", intVar);  
		return "personlistedit.jsp";
	}
	
	// edit record: finalize/save it (or get kicked back b/c errors)
	@PostMapping("/personpluscreate/{personId}/edit")
	public String displayAllEditOne(
			@Valid 
			@ModelAttribute("person") PersonMdl personMdl 
			, BindingResult result
			, Model model) {
		if (result.hasErrors()) {
			List<PersonMdl> intVar2 = personSrv.returnAll();
			model.addAttribute("personList", intVar2); 
			return "personlistedit.jsp";
		} else {
			personSrv.update(personMdl);
			return "redirect:/personpluscreate";
		}
	}
	
	
	

	// end of ctl
}
