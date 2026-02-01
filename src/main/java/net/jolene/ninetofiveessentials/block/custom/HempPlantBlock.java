package net.jolene.ninetofiveessentials.block.custom;

import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class HempPlantBlock extends BushBlock implements BonemealableBlock {
		
	public static final int MAX_AGE = 5;
	public static final IntegerProperty AGE = IntegerProperty.create("age", 0, MAX_AGE);
	//TODO: Top property, since top blocks are currently unimplemented upstream
	
	public HempPlantBlock(BlockBehaviour.Properties properties) {
		super(properties);
		this.registerDefaultState(stateDefinition.any()
				.setValue(AGE, 0));
	}
	
	@Override
	public MapCodec<? extends BushBlock> codec() {
		return simpleCodec(HempPlantBlock::new);
	}
	
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(AGE);
	}
	
	@Override
	protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		int age = state.getValue(AGE);
		if(age >= 3)
			return Shapes.box(0, 0, 0, 1, 1 + 0.25f * (age - 1), 1);
		else
			return Shapes.block();
	}
	
	@Override
	protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		int age = state.getValue(AGE);
		if(age >= MAX_AGE) return;
		
		int skyLight = level.getBrightness(LightLayer.SKY, pos);
		int blockLight = level.getBrightness(LightLayer.BLOCK, pos);
		
		//prefers grow lights
		if(blockLight > skyLight && blockLight >= 8) {
			if(random.nextBoolean()) level.setBlock(pos, state.setValue(AGE, age + 1), Block.UPDATE_CLIENTS);
		} else {
			if(skyLight >= 9 && random.nextFloat() < 0.05) level.setBlock(pos, state.setValue(AGE, age + 1), Block.UPDATE_CLIENTS);
		}
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
}
