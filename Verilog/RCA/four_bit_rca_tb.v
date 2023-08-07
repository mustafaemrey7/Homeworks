`timescale 1 ns/10 ps
module four_bit_rca_tb;
    reg [3:0] A;
	reg [3:0] B;
	reg Cin;
	wire [3:0] S;
	wire Cout;
	four_bit_rca UUT(.A(A), .B(B),.Cin(Cin), .S(S), .Cout(Cout));
    integer i,k,s;
	initial begin
		for(i=4'b0000;i<4'b1111;i=i+4'b0001)begin
            for(k=4'b0000;k<4'b1111;k=k+4'b0001)begin
                 for(s=1'b0;s<=1'b1;s=s+1'b1)begin
                       A=i;B=k;Cin=s;
                       #10;
                 end
            end
        end
	end
endmodule

