#include <iostream>
#include <conio.h>
#include <stdio.h>
#include <process.h>
#include <fstream>

using namespace std;
class airways
{
public:
    char flight_name[20];
    int flight_no;
    int seats;
    char source[30];
    char destination[30];
    float ticket_charge;
    float time_taken;

    void getdata();
    void showdata();
    void showall();
};

void airways::getdata()
{
    cout << "\n\n\t\t ***********************";
    cout << "\n\n\t\t # ENTER FLIGHT RECORD #";
    cout << "\n\n\t\t ***********************";
    cout << "\n\n\t\t # Flight no      :-->";
    cin >> flight_no;
    cout << "\n\n\t\t # Flight name    :-->";
    gets(flight_name);
    cout << "\n\n\t\t # No of seats    :--> ";
    cin >> seats;
    cout << "\n\n\t\t # Source         :-->   ";
    gets(source);
    cout << "\n\n\t\t # Destination    :-->   ";
    gets(destination);
    cout << "\n\n\t\t # Ticket charges :-->Rs.";
    cin >> ticket_charge;
    cout << "\n\n\t\t # Time taken     :-->";
    cin >> time_taken;
}

void airways::showdata()
{
    cout << "\n\n\t\t *****************";
    cout << "\n\n\t\t # FLIGHT RECORD #";
    cout << "\n\n\t\t *****************";
    cout << "\n\n\t\t # Flight no is     :-->" << flight_no;
    cout << "\n\n\t\t # Flight name is   :-->" << flight_name;
    cout << "\n\n\t\t # No of seats      :-->" << seats;
    cout << "\n\n\t\t # Source           :-->" << source;
    cout << "\n\n\t\t # Destination      :-->" << destination;
    cout << "\n\n\t\t # Ticket charges   :-->" << ticket_charge;
    cout << "\n\n\t\t # Time taken       :-->" << time_taken;
    cout << "\n\n\t\t #######################";
    getch();
}

void airways::showall()
{

    cout << "\n\n\t# " << flight_no << "     " << flight_name << "     ";
    cout << source << "     " << destination << "     ";
    cout << ticket_charge << endl;
}

/*********************************************************************
                Function prototypes
**********************************************************************/

void flight_record();
void add_flights();
void list_flights();
void search_flight();
void update_flight();
void delete_flight();

////////////////////////////////////////////////////////////////////

class cust
{
public:
    char customer_name[20];
    float customer_id;
    int age;
    char add[30];
    char nation[30];
    int phno;
    int mob;

    void getdata();
    void showdata();
    void showall();
};

void cust::getdata()
{
    cout << "\n\n\t\t ***********************";
    cout << "\n\n\t\t # ENTER FLIGHT RECORD #";
    cout << "\n\n\t\t ***********************";
    cout << "\n\n\t\t # Customer ID    :-->   ";
    cin >> customer_id;
    cout << "\n\n\t\t # Customer name  :-->   ";
    gets(customer_name);
    cout << "\n\n\t\t # Age            :-->   ";
    cin >> age;
    cout << "\n\n\t\t # Address        :-->   ";
    gets(add);
    cout << "\n\n\t\t # Nationallity   :-->   ";
    gets(nation);
    cout << "\n\n\t\t # Phone no       :-->   ";
    cin >> phno;
    cout << "\n\n\t\t # Mobile no.     :-->";
    cin >> mob;
}

void cust::showdata()
{
    cout << "\n\n\t\t *****************";
    cout << "\n\n\t\t # CUSTOMER RECORD #";
    cout << "\n\n\t\t *****************";
    cout << "\n\n\t\t # Customer ID      :-->" << customer_id;
    cout << "\n\n\t\t # Customer Name    :-->" << customer_name;
    cout << "\n\n\t\t # Age              :-->" << age;
    cout << "\n\n\t\t # Address          :-->" << add;
    cout << "\n\n\t\t # Nationallity     :-->" << nation;
    cout << "\n\n\t\t # Phone No.        :-->" << phno;
    cout << "\n\n\t\t # Mobile No.       :-->" << mob;
    cout << "\n\n\t\t #######################";
    getch();
}

void cust::showall()
{
    cout << "\n\n\t# " << customer_id << "     " << customer_name << "     " << age;
    cout << add << "     " << nation << "     ";
    cout << phno << "     " << mob << endl;
}
/**********************************************************************
            Function Prototypes
***********************************************************************/

void customer_record();
void add_customer();
void list_customer();
void search_customer();
void update_customer();
void delete_customer();

class book
{
public:
    int seat_no;
    int flight_no;
    float customer_id;
    int day;
    int month;
    int year;

    void getdata();
    void showdata();
    void showall();
};

void book::getdata()
{
    cout << "\n\n\t\t *********************************";
    cout << "\n\n\t\t # ENTER SEAT BOOKING RECORD #";
    cout << "\n\n\t\t *********************************";
    cout << "\n\n\t\t # Seat number    :-->   ";
    cin >> seat_no;
    cout << "\n\n\t\t # Day            :-->   ";
    cin >> day;
    cout << "\n\n\t\t # Month          :-->   ";
    cin >> month;
    cout << "\n\n\t\t # Year           :-->   ";
    cin >> year;
}

void book::showdata()
{
    cout << "\n\n\t\t *****************************";
    cout << "\n\n\t\t # SEAT BOOKING RECORD #";
    cout << "\n\n\t\t *****************************";
    cout << "\n\n\t\t # Customer ID      :-->" << customer_id;
    cout << "\n\n\t\t # Seat number      :-->" << seat_no;
    cout << "\n\n\t\t # Flight number    :-->" << flight_no;
    cout << "\n\n\t\t # Day              :-->" << day;
    cout << "\n\n\t\t # Month            :-->" << month;
    cout << "\n\n\t\t # Year             :-->" << year;
    cout << "\n\n\t\t ####################################";
    getch();
}

void book::showall()
{
    cout << "\n\n\t# " << customer_id << "     " << seat_no << "     " << flight_no;
    cout << day << "     " << month << "     " << year;
}

/***************************************************************
            Function Prototypes
****************************************************************/

void customer_id();
void seat_no();
void flight_no();
void day();
void month();
void year();
void seat_book();

//////////////////////////////////////////////////////////////////
void list();
void customer_id_wise();
void flight_wise();
void date_wise();
/////////////////////////////////////////////////////////////////

fstream f1, f2, f3;
airways ob1;
cust ob2;
book ob3;

void main()
{
    clrscr();
    int ch;
    do
    {
        cout << "\n\n\n\n\t\t**************************";
        cout << "\n\t\tAirways Reservation System";
        cout << "\n\t\t**************************\n\n\n";
        cout << "\n\t\t 1.Flight record" << endl;
        cout << "\n\t\t 2.Customer record" << endl;
        cout << "\n\t\t 3.Seat Booking" << endl;
        cout << "\n\t\t 4.Listing" << endl;
        cout << "\n\t\t 5.Exit" << endl;
        cout << "\n\t\t Enter your choice:-->";
        cin >> ch;
        switch (ch)
        {
        case 1:
            clrscr();
            flight_record();
            break;
        case 2:
            clrscr();
            customer_record();
            break;
        case 3:
            clrscr();
            seat_book();
            break;
        case 4:
            clrscr();
            list();
            break;
        case 5:
            break;
        default:
            cout << "\n\t\tInvalid choice";
            getch();
            clrscr();
            break;
        }
    } while (ch != 5);
}

//********** FUNCTION OF FLIGHT RECORD **********

void flight_record()
{
    clrscr();
    int fch;
    do
    {
        cout << "\n\n\t\t**************";
        cout << "\n\n\t\tOption-Flights";
        cout << "\n\n\t\t**************\n\n\t\t";
        cout << "\n\n\t\t 1.Add a flight ";
        cout << "\n\n\t\t 2.List all flights ";
        cout << "\n\n\t\t 3.Search for a flight";
        cout << "\n\n\t\t 4.Update a flight";
        cout << "\n\n\t\t 5.Delete a flight";
        cout << "\n\n\t\t 6.Back to main menu";
        cout << "\n\n\t\t Enter your choice:-->";
        cin >> fch;
        switch (fch)
        {
        case 1:
            clrscr();
            add_flights();
            break;
        case 2:
            clrscr();
            list_flights();
            break;
        case 3:
            clrscr();
            search_flight();
            break;
        case 4:
            clrscr();
            update_flight();
            break;
        case 5:
            clrscr();
            delete_flight();
            break;
        case 6:
            clrscr();
            break;
        default:
            cout << "\n\n\t\t Invalid choice" << endl;
            getch();
            break;
        }

    } while (fch != 6);
    clrscr();
}

// *********  ADD FLIGHTS  *********

void add_flights(void)
{
    char yn;
    f1.open("abc2.dat", ios::in | ios::out | ios::ate);
    ob1.getdata();

    cout << "\n\n\t\t # Do you want to store the data (y/n):-->";
    cin >> yn;
    if (yn == 'y' || yn == 'Y')
    {
        f1.write((char *)&ob1, sizeof(ob1));
        cout << "\n\n\t\t***// DATA IS STORED \\***";
    }
    else
        cout << "\n\n\t\t***// DATA IS NOT STORED \\***";

    f1.close();
    getch();
    clrscr();
}
//  ************* SEARCH A FLIGHT RECORD *************

void search_flight()
{
    int c = 0;
    float fno;
    cout << "\n\n\t\t  # Enter flight no :-->";
    cin >> fno;
    f1.open("abc2.dat", ios::in);

    while (f1.read((char *)&ob1, sizeof(ob1)))
    {
        if (fno == ob1.flight_no)
        {
            c = 1;
            break;
        }
    }
    if (c == 0)
    {
        cout << "\n\n\t\t **// Not found \\**";

        getch();
        f1.close();
        return;
    }
    ob1.showdata();
    getch();
    f1.close();
    clrscr();
}
//********** MODIFY FLIGHT DETAILS **********

void update_flight()
{
    int c = 0;
    float fno;
    cout << "\n\n\t\t  # Enter flight no :-->";
    cin >> fno;
    f1.open("abc2.dat", ios::in | ios::out | ios::ate);
    f1.seekg(0);

    while (f1.read((char *)&ob1, sizeof(ob1)))
    {
        if (fno == ob1.flight_no)
        {
            c = 1;
            break;
        }
    }
    f1.clear();

    if (c == 0)
    {
        cout << "\n\n\t\t **// Not found \\**";
        getch();
        f1.close();
        return;
    }

    char yn;
    int pos = f1.tellp();
    f1.seekp(pos - sizeof(ob1));
    ob1.showdata();
    cout << "\n\n\t\t # Do you want to modify the data (y/n):-->";
    cin >> yn;

    if (yn == 'y' || yn == 'Y')
    {
        float new_ticket;
        cout << "\n\n\t\t Enter the new ticket charges:-->";
        cin >> new_ticket;
        ob1.ticket_charge = new_ticket;
        f1.write((char *)&ob1, sizeof(ob1));
        cout << "\n\n\t\t***// DATA IS MODIFIED \\***";
    }
    else
        cout << "\n\n\t\t***// DATA IS NOT MODIFIED \\***";

    getch();
    f1.close();
    clrscr();
}

//********** LIST ALL FLIGHTS **********

void list_flights()
{
    clrscr();
    f1.open("abc2.dat", ios::in);
    while (f1.read((char *)&ob1, sizeof(ob1)))
    {
        ob1.showall();
    }
    getch();
    f1.close();
    clrscr();
}
//********** DELETE A FLIGHT **********

void delete_flight()
{
    int c = 0;
    int fno;
    cout << "\n\n\t\t  # Enter flight no :-->";
    cin >> fno;
    f1.open("abc2.dat", ios::in);
    f2.open("temp.dat", ios::out);

    while (f1.read((char *)&ob1, sizeof(ob1)))
    {
        if (fno != ob1.flight_no)
            f2.write((char *)&ob1, sizeof(ob1));
        else
            c = 1;
    }
    f1.close();
    f2.close();
    if (c == 0)
    {
        cout << "\n\n\t\t **// Not found \\**";
        getch();
        return;
    }

    remove("abc2.dat");
    rename("temp.dat", "abc2.dat");
    cout << "\n\n\t\t***// DATA IS DELETED \\***";

    getch();
    clrscr();
}

//********** FUNCTION OF CUSTOMER RECORD **********

void customer_record(void)
{
    clrscr();
    int cch;
    do
    {
        cout << "\n\n\t\t****************";
        cout << "\n\n\t\tOption-Customer";
        cout << "\n\n\t\t****************\n\n\t\t";
        cout << "\n\n\t\t 1.Add a Customer ";
        cout << "\n\n\t\t 2.List all Customer ";
        cout << "\n\n\t\t 3.Search for a Customer";
        cout << "\n\n\t\t 4.Update a Customer";
        cout << "\n\n\t\t 5.Delete a Customer";
        cout << "\n\n\t\t 6.Back to main menu";
        cout << "\n\n\t\t Enter your choice:-->";
        cin >> cch;
        switch (cch)
        {
        case 1:
            clrscr();

            add_customer();
            break;
        case 2:
            clrscr();
            list_customer();
            break;
        case 3:
            clrscr();
            search_customer();
            break;
        case 4:
            clrscr();
            update_customer();
            break;
        case 5:
            clrscr();
            delete_customer();
            break;
        case 6:
            clrscr();
            break;
        default:
            cout << "\n\n\t\t Invalid choice" << endl;
            getch();
            break;
        }
    } while (cch != 6);
    clrscr();
}

/////////////////////

// *********  ADD CUSTOMER  *********
void add_customer(void)
{
    char yn;
    f1.open("abc3.dat", ios::in | ios::out | ios::ate);
    ob2.getdata();
    cout << "\n\n\t\t # Do you want to store the data (y/n):-->";
    cin >> yn;
    if (yn == 'y' || yn == 'Y')
    {
        f1.write((char *)&ob2, sizeof(ob2));
        cout << "\n\n\t\t***// DATA IS STORED \\***";
    }
    else
        cout << "\n\n\t\t***// DATA IS NOT STORED \\***";

    f1.close();
    getch();
    clrscr();
}

//  ************* SEARCH A CUSTOMER RECORD *************

void search_customer()
{
    int c = 0;
    float fno;
    cout << "\n\n\t\t  # Enter customer id :-->";
    cin >> fno;
    f1.open("abc3.dat", ios::in | ios::out | ios::ate);
    f1.seekg(0);
    while (f1.read((char *)&ob2, sizeof(ob2)))
    {
        if (fno == ob2.customer_id)
        {
            c = 1;
            break;
        }
    }
    f1.clear();

    if (c == 0)
    {
        cout << "\n\n\t\t **// Not found \\**";
        getch();
        f1.close();
        return;
    }

    //	 int pos=f1.tellp();
    // f1.seekp(pos-sizeof(ob2));
    ob2.showdata();
    getch();
    f1.close();
    clrscr();
}

//********** MODIFY CUSTOMER DETAILS **********
void update_customer()
{
    int c = 0;
    char yn;
    float fno;
    cout << "\n\n\t\t  # Enter customer id :-->";
    cin >> fno;
    f1.open("abc3.dat", ios::in | ios::out | ios::ate);
    f1.seekg(0);

    while (f1.read((char *)&ob2, sizeof(ob2)))
    {
        if (fno == ob2.customer_id)
        {
            c = 1;
            break;
        }
    }
    f1.clear();

    if (c == 0)
    {
        cout << "\n\n\t\t **// Not found \\**";
        getch();
        f1.close();
        return;
    }

    int pos = f1.tellp();
    f1.seekp(pos - sizeof(ob2));
    ob2.showdata();

    cout << "\n\n\t\t # Do you want to modify the data (y/n):-->";
    cin >> yn;

    if (yn == 'y' || yn == 'Y')
    {
        float phno1, mob1;
        cout << "RECORDS TO BE MODIFIED ";
        cout << "\n\n\t\t # Phone no       :-->   ";
        cin >> phno1;
        cout << "\n\n\t\t # Mobile no.     :-->";
        cin >> mob1;
        ob2.mob = mob1;
        ob2.phno = phno1;
        f1.write((char *)&ob2, sizeof(ob2));
        cout << "\n\n\t\t***// DATA IS MODIFIED \\***";
    }
    else
        cout << "\n\n\t\t***// DATA IS NOT MODIFIED \\***";

    getch();
    f1.close();
    clrscr();
}

//********** LIST ALL CUSTOMER **********

void list_customer()
{
    clrscr();
    f1.open("abc3.dat", ios::in);
    f1.seekg(0);

    while (f1.read((char *)&ob2, sizeof(ob2)))
    {
        ob2.showall();
    }
    f1.clear();
    getch();
    f1.close();
    clrscr();
}

//********** DELETE A CUSTOMER **********

void delete_customer()
{
    int c = 0;
    float fno;
    cout << "\n\n\t\t  # Enter customer id :-->";
    cin >> fno;

    f1.open("abc3.dat", ios::in | ios::out | ios::ate);
    f2.open("temp.dat", ios::in | ios::out | ios::ate);
    f1.seekg(0);

    while (f1.read((char *)&ob2, sizeof(ob2)))
    {
        if (fno != ob2.customer_id)
            f2.write((char *)&ob2, sizeof(ob2));
        else
            c = 1;
    }
    f1.clear();

    f1.close();
    f2.close();

    if (c == 0)
    {
        cout << "\n\n\t\t **// Not found \\**";
        getch();
        f1.close();
        f2.close();
        return;
    }

    remove("abc3.dat");
    rename("temp.dat", "abc3.dat");
    cout << "\n\n\t\t***// DATA IS DELETED \\***";
    getch();
    clrscr();
}

//////////////////////////////////

void seat_book()
{
    clrscr();
    cout << "\n\n\t\t***************************************\n";
    cout << "\n\n\t\t************ SEAT BOOKING *************\n";
    cout << "\n\n\t\t***************************************\n";

    //  ************* SEARCH A FLIGHT RECORD *************

    int c2 = 0;
    float fno2;

    cout << "\n\n\t\t  # Enter flight no :-->";
    cin >> fno2;

    f1.open("abc2.dat", ios::in | ios::out | ios::ate);
    f1.seekg(0);

    while (f1.read((char *)&ob1, sizeof(ob1)))
    {
        if (fno2 == ob1.flight_no)
        {
            c2 = 1;
            break;
        }
    }
    f1.clear();

    if (c2 == 0)
    {
        cout << "\n\n\t\t **// Not found \\**";
        getch();
        f1.close();
        return;
    }
    ob1.showdata();

    //  ************* SEARCH A CUSTOMER RECORD *************

    int c1 = 0;
    float fno1;
    cout << "\n\n\t\t  # Enter customer id :-->";
    cin >> fno1;
    f2.open("abc3.dat", ios::in | ios::out | ios::ate);
    f2.seekg(0);
    while (f2.read((char *)&ob2, sizeof(ob2)))
    {
        if (fno1 == ob2.customer_id)
        {
            c1 = 1;
            break;
        }
    }
    f2.clear();

    if (c1 == 0)
    {
        cout << "\n\n\t\t **// Not found \\**";
        getch();
        f1.close();
        f2.close();
        return;
    }

    ob2.showdata();
    getch();
    f1.close();
    clrscr();

    f3.open("book.dat", ios::in | ios::out | ios::ate);
    ob3.getdata();
    ob3.flight_no = fno2;
    ob3.customer_id = fno1;
    f3.write((char *)&ob3, sizeof(ob3));
    cout << " Record Entered .... ";
    getche();
    f3.close();
}
////////////////////////////////////////////

void list(void)
{
    clrscr();
    int sch;
    do
    {
        cout << "\n\n\n\n\t\t**************************";
        cout << "\n\t\tSeat Reservation System";
        cout << "\n\t\t**************************\n\n\n";
        cout << "\n\t\t 1.Flight No. wise" << endl;
        cout << "\n\t\t 2.Customer ID wise" << endl;
        cout << "\n\t\t 3.Date wise" << endl;
        cout << "\n\t\t 4.Return" << endl;
        cout << "\n\t\t Enter your choice:-->";
        cin >> sch;
        switch (sch)
        {
        case 1:
            clrscr();
            flight_wise();
            break;
        case 2:
            clrscr();
            customer_id_wise();
            break;
        case 3:
            clrscr();
            date_wise();
            break;
        case 4:
            break;
        default:
            cout << "\n\t\tInvalid choice";
            getch();
            clrscr();
        }
        clrscr();
    } while (sch != 4);
}

void flight_wise()
{
    int c = 0;
    float fno;
    fstream f4;
    f4.open("temp.dat", ios::in | ios::out | ios::ate);
    cout << "\n\n\t\t  # Enter flight no :-->";
    cin >> fno;
    f1.open("book.dat", ios::in);
    f1.seekg(0);

    while (f1.read((char *)&ob3, sizeof(ob3)))
    {
        if (fno == ob3.flight_no)
        {
            c = 1;
            f4.write((char *)&ob3, sizeof(ob3));
        }
    }
    f1.clear();
    f4.clear();
    if (c == 0)
    {
        cout << "\n\n\t\t **// Not found \\**";
        getch();
        f1.close();
        f4.close();
        remove("temp.dat");
        return;
    }
    f4.seekg(0);
    while (f4.read((char *)&ob3, sizeof(ob3)))
    {
        ob3.showdata();
        getch();
    }
    f1.close();
    f4.close();
    remove("temp.dat");
    clrscr();
}

void customer_id_wise()
{
    int c = 0;
    float fno;
    cout << "\n\n\t\t  # Enter customer id :-->";
    cin >> fno;
    f1.open("book.dat", ios::in);
    f1.seekg(0);
    while (f1.read((char *)&ob3, sizeof(ob3)))
    {
        if (fno == ob3.customer_id)
        {
            c = 1;
            break;
        }
    }
    f1.clear();

    if (c == 0)
    {
        cout << "\n\n\t\t **// Not found \\**";
        getch();
        f1.close();
        return;
    }

    //	 int pos=f1.tellp();
    // f1.seekp(pos-sizeof(ob2));
    ob3.showdata();
    getch();
    f1.close();
    clrscr();
}

void date_wise()
{
}
///////////////////////////////////////////////////