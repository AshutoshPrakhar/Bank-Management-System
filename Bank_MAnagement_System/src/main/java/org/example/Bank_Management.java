package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Bank_Management {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/Management_System";
        String username = "root";
        String password = "AshutoshPrakhar@123#A";
        try{
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();



            Scanner sc = new Scanner(System.in);
            System.out.println(" 1. Create a new account  \n 2. Check Balance and details of the Account3 \n 3. Deposit the amount \n 4. Withdraw the amount \n 5. Exit \n\nENTER YOUR CHOICE :: ");
            int choice = sc.nextInt();
            switch(choice){
                case 1:
                    ResultSet MaxIdResSet = statement.executeQuery("select Max(Serial_number) as max_Serial_number  from Account_details");
                    int max_num = 0;
                    while(MaxIdResSet.next()){
                        max_num = MaxIdResSet.getInt("max_Serial_number");
                        System.out.println("No. of Accounts :"+max_num);
                        System.out.println("Fill the necessary requirements for creating account");
                    }
                    max_num++;
                    System.out.println("Enter New Account Number : ");
                    int Account_number = sc.nextInt();
                    System.out.println("Enter Account Holder Name : ");
                    String Account_holder = sc.next();
                    System.out.println("Enter type of Bank Account : ");
                    String Account_type = sc.next();
                    System.out.println("Enter the balance : ");
                    int Balance = sc.nextInt();

                    int rowCount = statement.executeUpdate("insert into Account_details values("+max_num+","+Account_number+",'"+Account_holder+"','"+Account_type+"',"+Balance+")");
                    if(rowCount > 0) System.out.println("Data inserted !");
                    else System.out.println("Data insertion failed !");
                    System.out.println("*******************************");
                    System.out.println("Thank you for using the Bank Management System. \nGoodbye! \nHave a Nice Day!");
                case 2:
                    System.out.println("Enter Account No. to fetch details: ");
                    int accNum = sc.nextInt();
                    ResultSet fetchBalance = statement.executeQuery("SELECT * FROM Account_details WHERE Account_number = " + accNum);
                    System.out.println("******** ACCOUNT DETAILS ***********");
                    while(fetchBalance.next()){
                        System.out.println("Account No. : "+fetchBalance.getInt("Account_number"));
                        System.out.println("Account Holder : "+fetchBalance.getString("Account_holder"));
                        System.out.println("Account Type : "+fetchBalance.getString("Account_type"));
                        System.out.println("Balance : "+fetchBalance.getString("Balance"));
                        System.out.println("------------------------------------------------------");
                    }
                    System.out.println("*******************************");
                    System.out.println("Thank you for using the Bank Management System. \nGoodbye! \nHave a Nice Day!");
                    break;
                case 3:
                    System.out.println("Enter Account No. for adding more balance : ");
                    int Acc_num = sc.nextInt();
                    System.out.println("Amount to add in your Account : ");
                    int new_amount = sc.nextInt();
                    ResultSet checkAccount = statement.executeQuery("SELECT Balance FROM Account_details WHERE Account_number = " + Acc_num);
                    if(checkAccount.next()){
                        int currBalance = checkAccount.getInt("Balance");
                        int updateBalance = currBalance + new_amount;
                        int updateCount = statement.executeUpdate("UPDATE Account_details SET Balance = " + updateBalance + " WHERE Account_number = " + Acc_num);
                        if(updateCount > 0) System.out.println("Balance updated successfully!");
                        else System.out.println("Balance update failed!");
                    } else {
                        System.out.println("Account not found!");
                    }
                    System.out.println("*******************************");
                    System.out.println("Thank you for using the Bank Management System. \nGoodbye! \nHave a Nice Day!");
                    break;
                case 4:
                    System.out.println("Enter Account No. for withdrawing money : ");
                    int acc_num = sc.nextInt();
                    System.out.println("Enter Amount to withdraw from your Account : ");
                    int withdraw = sc.nextInt();
                    ResultSet checkBalance = statement.executeQuery("Select Balance FROM Account_details WHERE Account_number = "+acc_num);
                    if(checkBalance.next()){
                        int current_balance = checkBalance.getInt("Balance");
                        int update_balance = current_balance-withdraw;
                        int update = statement.executeUpdate("UPDATE Account_details SET Balance = " + update_balance + " WHERE Account_number = " + acc_num);
                        if(update > 0) System.out.println("Balance update successfully !!");
                        else System.out.println("Balance update failed !!");
                    }
                    else {
                        System.out.println("Account not fount !!");
                    }
                    System.out.println("*******************************");
                    System.out.println("Thank you for using the Bank Management System. \nGoodbye! \nHave a Nice Day!");
                    break;
                case 5:
                    System.out.println("*******************************");
                    System.out.println("Thank you for using the Bank Management System. \nGoodbye! \nHave a Nice Day!");
                    break;
                default:
                    System.out.println("Enter valid option...");
                    break;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
