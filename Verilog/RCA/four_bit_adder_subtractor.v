module four_bit_adder_subtractor(A, B, subtract, Result, Cout);
    input [3:0] A;
    input [3:0] B;
    input subtract;
    output [3:0] Result;
    output Cout;

    wire [3:0] complementB,selectedBit;
    two_s_complement f1(B,complementB);
    four_bit_2x1_mux f2(complementB, B, subtract, selectedBit);
    four_bit_rca f3(A,selectedBit,0,Result,Cout);
endmodule

