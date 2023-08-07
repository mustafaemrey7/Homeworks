`timescale 1 ns/10 ps
module full_adder_tb;

    reg A,B,Cin;
    // Your code goes here.  DO NOT change anything that is already given! Otherwise, you will not be able to pass the tests!
    wire S,Cout;
    
    full_adder UUT(A,B,Cin,S,Cout);
    
    initial begin
  A=0;B=0;Cin=0;
  #10; A=0;B=0;Cin=1;
  #10; A=0;B=1;Cin=0;
  #10; A=0;B=1;Cin=1;
  #10; A=1;B=0;Cin=0;
  #10; A=1;B=0;Cin=1;
  #10; A=1;B=1;Cin=0;
  #10; A=1;B=1;Cin=1;
  
    
    
    end
    
endmodule