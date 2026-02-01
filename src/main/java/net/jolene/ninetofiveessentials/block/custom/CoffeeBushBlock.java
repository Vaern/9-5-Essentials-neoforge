package net.jolene.ninetofiveessentials.block.custom;

import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class CoffeeBushBlock extends BushBlock implements BonemealableBlock {
	
	public static final int MAX_AGE = 5;
	public static final IntegerProperty AGE = IntegerProperty.create("age", 0, MAX_AGE);
	
	public CoffeeBushBlock(BlockBehaviour.Properties properties) {
		super(properties);
		this.registerDefaultState(stateDefinition.any().setValue(AGE, 0));
	}
	
	@Override
	public MapCodec<? extends BushBlock> codec() {
		return simpleCodec(CoffeeBushBlock::new);
	}
	
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(AGE);
	}

	@Override
	public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
		return state.getValue(AGE) < MAX_AGE;
	}

	@Override
	public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
		return state.getValue(AGE) < MAX_AGE;
	}

	@Override
	public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
		int age = state.getValue(AGE);
		if(age < MAX_AGE)
			level.setBlock(pos, state.setValue(AGE, age + 1), Block.UPDATE_CLIENTS);
	}
	
	//TODO: at the moment, no random tick growth is implemented upstream for coffee

}
