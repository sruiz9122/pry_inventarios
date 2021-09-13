/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sergiocompany.project15_mvc.controller;

import com.sergiocompany.project15_mvc.model.Product;
import com.sergiocompany.project15_mvc.model.ProductDAO;
import com.sergiocompany.project15_mvc.view.Inventarios.Inventarios;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Sergio
 */
public class ProductController implements ActionListener {

    DefaultTableModel tableModel = new DefaultTableModel();
    ProductDAO productDAO;
    Inventarios inventarios;

    public ProductController() {

        productDAO = new ProductDAO();
        initView();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == inventarios.jButtonGuardar) {
            insertData();
            listData();
        } else if (e.getSource() == inventarios.jButtonListar) {
            listData();
        }
    }

    private void initView() {
        inventarios = new Inventarios();
        tableModel = (DefaultTableModel) inventarios.jTableProductosGuardados.getModel();
        inventarios.setVisible(true);
        inventarios.jButtonGuardar.addActionListener(this);
        inventarios.jButtonListar.addActionListener(this);
    }

    private void insertData() {
        Product product = new Product();
        product.setCode(Integer.parseInt(inventarios.jTextFieldCodigo.getText()));
        product.setName(inventarios.jTextFieldNombre.getText());
        product.setDescription(inventarios.jTextAreaDescripcion.getText());
        product.setPrice(Double.parseDouble(inventarios.jTextFieldPrecio.getText()));
        product.setStock(Integer.parseInt(inventarios.jTextFieldExistencia.getText()));
        int stateQuery = productDAO.insert(product);
        if (stateQuery == 1) {
            JOptionPane.showMessageDialog(inventarios, "Error en el ingreso del producto, por favor validar");
        } else {
            JOptionPane.showMessageDialog(inventarios, "Producto ingresado correctamente");
        }

        cleanFields();

    }

    private void listData() {

        cleanTable();
        inventarios.jTableProductosGuardados.setModel(tableModel);
        List<Product> products = productDAO.listProducts();
        Object[] objeto = new Object[6];
        for (int i = 0; i < products.size(); i++) {
            objeto[1] = products.get(i).getCode();
            objeto[2] = products.get(i).getName();
            objeto[3] = products.get(i).getDescription();
            objeto[4] = products.get(i).getPrice();
            objeto[5] = products.get(i).getStock();
            tableModel.addRow(objeto);
        }

    }

    private void cleanTable() {

        for (int i = 0; i < inventarios.jTableProductosGuardados.getRowCount(); i++) {
            tableModel.removeRow(i);
            i = i - 1;
        }
    }

    private void cleanFields() {
        inventarios.jTextFieldCodigo.setText("");
        inventarios.jTextFieldNombre.setText("");
        inventarios.jTextAreaDescripcion.setText("");
        inventarios.jTextFieldPrecio.setText("");
        inventarios.jTextFieldExistencia.setText("");
    }

}
