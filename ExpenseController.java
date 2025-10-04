package com.expensemate.controller;

import com.expensemate.model.Expense;
import com.expensemate.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
public class ExpenseController {

    @Autowired
    private ExpenseRepository expenseRepository;

    @GetMapping("/")
    public String viewHome(Model model) {
        List<Expense> expenses = expenseRepository.findAll();
        model.addAttribute("expenses", expenses);
        return "index";
    }

    @GetMapping("/add")
    public String addExpenseForm(Model model) {
        model.addAttribute("expense", new Expense());
        return "add";
    }

    @PostMapping("/save")
    public String saveExpense(@ModelAttribute Expense expense) {
        if (expense.getDate() == null) {
            expense.setDate(LocalDate.now());
        }
        expenseRepository.save(expense);
        return "redirect:/";
    }
}
