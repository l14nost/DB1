package org.example.Class;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shopingcart")
public class ShoppingCart {
    @Id
    private int idUser;
    @Id
    private int idProduct;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }



    @Override
    public String toString() {
        return "ShoppingCart{" +
                "idUser=" + idUser +
                ", idProduct=" + idProduct +
                '}';
    }
}
