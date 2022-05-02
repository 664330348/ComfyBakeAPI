package com.revature.comfybake.User.Wallet;

import javax.persistence.*;

@Entity
@Table(name="user_wallet")
public class UserWallet {

    @Id
    @Column(name="user_wallet_id")
    private String walletId;

    @Column(columnDefinition = "numeric(9,2)")
    private double totalBalance;

    public UserWallet() {
        this.totalBalance = 1000;
    }

    public UserWallet(String walletId, double totalBalance) {
        this.walletId = walletId;
        this.totalBalance = totalBalance;
    }

    public String getWalletId() {
        return walletId;
    }

    public void setWalletId(String walletId) {
        this.walletId = walletId;
    }

    public double getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(double totalBalance) {
        this.totalBalance = totalBalance;
    }

    @Override
    public String toString() {
        return "UserWallet{" +
                "walletId='" + walletId + '\'' +
                ", totalBalance=" + totalBalance +
                '}';
    }
}
