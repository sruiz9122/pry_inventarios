/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sergiocompany.project15_mvc;

import com.sergiocompany.project15_mvc.controller.ProductController;
import java.sql.SQLException;

/**
 *
 * @author Sergio
 */
public class Main {
    public static void main(String[] args) throws SQLException {
        ProductController productController = new ProductController();
    }
}
