`timescale 1us/1ns
module weapons_control_unit(
    input target_locked,
    input clk,
    input rst,
    input fire_command,
    output reg launch_missile,
    output [3:0] remaining_missiles,
    output [1:0] WCU_state
);  
    parameter IDLE = 2'b00,
    TARGET_LOCKED = 2'b01,
    FIRE = 2'b10,
    OUT_OF_AMMO = 2'b11;
    reg [1:0] state;
    reg [3:0] missiles;
always @(posedge clk) begin
    if (rst) begin
        state <= IDLE;
        missiles <= 4'b0100;
        launch_missile = 0;
    end
    state = WCU_state;
    launch_missile = 0;
    case (state)
        IDLE: begin
            if (target_locked) begin
                state = TARGET_LOCKED;
            end
        end
        TARGET_LOCKED: begin
            if (!target_locked) begin
                state = IDLE;
            end 
            else if (fire_command) begin
                state <= FIRE;
                launch_missile = 1;
                missiles <= missiles - 1;
                #10;
                launch_missile = 0;          
            end
        end
        FIRE: begin
            if (missiles > 0) begin         
                if (target_locked) begin
                    state = TARGET_LOCKED;
                end 
                else begin
                    state = IDLE;
                end
            end 
            else begin
                state = OUT_OF_AMMO;
            end
        end
        OUT_OF_AMMO: begin
            
        end
    endcase 
end
    assign remaining_missiles = missiles;
    assign WCU_state = state;
endmodule