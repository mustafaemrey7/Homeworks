`timescale 1ns/10ps
module four_bit_2x1_mux_tb;
    reg [3:0] In0,In1;
	reg Select;
	wire [3:0] Out;
	four_bit_2x1_mux UUT(In1, In0, Select, Out);
    integer i,k,s;
    initial begin
        for(i=4'b0000;i<4'b1111;i=i+4'b0001)begin
            for(k=4'b0000;k<4'b1111;k=k+4'b0001)begin
                 for(s=0;s<2;s=s+1)begin
                       In0=i;In1=k;Select=s;
                       #10;
                 end
            end
        end
    end
endmodule



