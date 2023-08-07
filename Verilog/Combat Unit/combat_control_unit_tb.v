`timescale 1us/1ns

module kizilelma_tb;

    reg rst;
    reg track_target_command; 
    reg clk; 
    reg radar_echo; 
    reg fire_command;

    wire [13:0] distance_to_target; 
    wire trigger_radar_transmitter; 
    wire launch_missile;

    wire [1:0] TTU_state;
    wire [1:0] WCU_state;
    wire [3:0] remaining_missiles;

    combat_control_unit uut(
        rst, 
        track_target_command,  
        clk,  
        radar_echo,  
        fire_command, 
        distance_to_target, 
        trigger_radar_transmitter,  
        launch_missile, 
        TTU_state, 
        WCU_state, 
        remaining_missiles);

    initial begin
        // Your code goes here.  DO NOT change anything that is already given! Otherwise, you will not be able to pass the tests!
        // Please inspect the provided waveforms very carefully and try to produce the same results.
        // Make sure to use $finish statement to avoid infinite loops.
    end


    initial begin
        clk = 0;
        forever begin
            #5;
            clk = ~clk;
        end
    end

endmodule