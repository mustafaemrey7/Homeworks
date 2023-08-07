`timescale 1ns/1ps
module four_bit_adder_subtractor_tb;
    reg [3:0] A,B;
	reg Subtract;
	wire [3:0] Out;
	wire Cout;
	four_bit_adder_subtractor UUT(A, B, Subtract, Out,Cout);
    integer i,k,s;
    initial begin
        for(i=4'b0000;i<4'b1111;i=i+4'b0001)begin
            for(k=4'b0000;k<4'b1111;k=k+4'b0001)begin
                 for(s=0;s<2;s=s+1)begin
                       A=i;B=k;Subtract=s;
                       #10;
                 end
            end
        end
    end
endmodule
