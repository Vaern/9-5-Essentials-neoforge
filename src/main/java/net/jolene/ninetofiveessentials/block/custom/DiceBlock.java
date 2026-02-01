package net.jolene.ninetofiveessentials.block.custom;

import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;

public class DiceBlock extends FallingBlock {
	public static final DirectionProperty FACING = BlockStateProperties.FACING;
	
	public DiceBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(stateDefinition.any().setValue(FACING, Direction.UP));
	}
	
	@Override
	public MapCodec<? extends FallingBlock> codec() {
		return simpleCodec(DiceBlock::new);
	}
	
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}
	
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return this.defaultBlockState();
	}
	
	@Override
	public void onLand(Level level, BlockPos pos, BlockState state, BlockState replaceableState, FallingBlockEntity fallingBlock) {
		//never called on the clientside, but just in case
		if(!level.isClientSide) {//TODO: sounds a bit weird without a landing sound effect
			Direction random = Direction.getRandom(level.getRandom());
			
			level.setBlock(pos, state.setValue(FACING, random), UPDATE_ALL);
			
			((ServerLevel) level).sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, state), 
					pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 20, 0.3, 0.3, 0.3, 0.1);
		}
	}
	
	@Override
	public int getDustColor(BlockState state, BlockGetter level, BlockPos pos) {
		return 0;
	}
	
	private int getRollFromFacing(Direction dir) {
		return switch (dir) {
		case UP -> 1;
		case DOWN -> 2;
		case NORTH -> 3;
		case SOUTH -> 4;
		case WEST -> 5;
		case EAST -> 6;
		};
	}
	
	@Override
	protected boolean hasAnalogOutputSignal(BlockState state) {
		return true;
	}
	
	@Override
	protected int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos) {
		return getRollFromFacing(state.getValue(FACING));
	}
}
