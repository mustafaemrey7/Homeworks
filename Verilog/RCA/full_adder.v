module full_adder(
    input A,
    input B,
    input Cin,
    output S,
    output Cout
);
    wire w1;
    wire w2;
    wire w3;
    assign w1 = A ^ B;
    assign w2 = w1 & Cin;
    assign w3 = A&B;
    assign S   = w1 ^ Cin;
    assign Cout = w2 | w3;
 
endmodule