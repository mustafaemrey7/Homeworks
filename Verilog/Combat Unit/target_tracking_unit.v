`timescale 1us/1ns

module target_tracking_unit(
    input rst,
    input track_target_command,
    input clk,
    input echo,
    output reg trigger_radar_transmitter,
    output reg [13:0] distance_to_target,
    output reg target_locked,
    output [1:0] TTU_state
);

    reg [1:0] state;
    reg [1:0] next_state;
    parameter IDLE = 2'b00;
    parameter TRANSMIT = 2'b01;
    parameter LISTEN_FOR_ECHO = 2'b10;
    parameter TRACK = 2'b11;
    reg [7:0] t1;
    reg [7:0] listenTime;
    reg [7:0] trackTime;
    reg [7:0] counter;
    
    always @(*) begin
        if(rst) begin
            next_state <= IDLE;
            trigger_radar_transmitter <= 0;
            distance_to_target <= 0;
            target_locked <= 0;
        end else begin
            case(state)
                IDLE: begin
                    if(track_target_command) begin
                        
                        trigger_radar_transmitter <= 1;
                        next_state <= TRANSMIT;
                    end
                end
                TRANSMIT: begin
                    
                    if(counter >= t1) begin
                        trigger_radar_transmitter <= 0;
                        next_state <= LISTEN_FOR_ECHO;
                        end
                 end
                 LISTEN_FOR_ECHO: begin
                    if(echo) begin
                    target_locked <= 1;
                    next_state <= TRACK;
                    end 
                    else if(counter >= listenTime) begin
                        next_state <= IDLE;
                    end
                 end
                 TRACK: begin
                    if(track_target_command) begin
                        next_state <= TRANSMIT;
                    end 
                    else if(counter >= trackTime) begin
                        next_state <= IDLE;
                    end
                end
        endcase
        end
    end
    always @(posedge clk ) begin
        state=next_state;
    end
  initial begin
        t1 = 50;
        listenTime = 100;
        trackTime = 300;
        
    end
    assign TTU_state = state;  
endmodule
    

