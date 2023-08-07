module four_bit_rca(
    input [3:0] A,
    input [3:0] B,
    input Cin,
    output [3:0] S,
    output Cout
);
    wire c1; 
    wire c2;
    wire c3;
	full_adder fa0(.A(A[0]), .B(B[0]), .Cin(Cin), .S(S[0]), .Cout(c1));
	full_adder fa1(.A(A[1]), .B(B[1]), .Cin(c1), .S(S[1]), .Cout(c2));
	full_adder fa2(.A(A[2]), .B(B[2]), .Cin(c2), .S(S[2]), .Cout(c3));
	full_adder fa3(.A(A[3]), .B(B[3]), .Cin(c3), .S(S[3]), .Cout(Cout));

endmodule

