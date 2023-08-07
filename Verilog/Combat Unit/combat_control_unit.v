`timescale 1us/1ns

module combat_control_unit(
    input rst,
    input track_target_command, 
    input clk, 
    input radar_echo, 
    input fire_command,
    output [13:0] distance_to_target, 
    output trigger_radar_transmitter, 
    output launch_missile,
    output [1:0] TTU_state,
    output [1:0] WCU_state,
    output [3:0] remaining_missiles
);
    wire trigger_radar_transmitter1;
    wire [13:0] distance_to_target1;
    wire target_locked1;
    wire [1:0] TTU_state1;
    wire launch_missile1;
    wire [3:0] remaining_nissiles1;
    wire [1:0] WCU_state1;
    target_tracking_unit TTU(rst,track_target_command,clk,radar_echo,trigger_radar_transmitter1,distance_to_target1,target_locked1,TTU_state1);
    weapons_control_unit WCU(target_locked1,clk,rst,fire_command,launch_missile1, remaining_missiles1,WCU_state1);
    
    assign trigger_radar_transmitter =trigger_radar_transmitter1;
    assign distance_to_target=distance_to_target1;
    assign launch_missile= launch_missile1;
    assign remaining_missiles=remaining_missiles1;
    assign TTU_state=TTU_state1;
    assign WCU_state=WCU_state1;
    
    

endmodule