package com.example.battletanks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_DPAD_DOWN
import android.view.KeyEvent.KEYCODE_DPAD_LEFT
import android.view.KeyEvent.KEYCODE_DPAD_RIGHT
import android.view.KeyEvent.KEYCODE_DPAD_UP
import android.view.Menu
import android.view.MenuItem
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.FrameLayout
import androidx.core.view.marginLeft
import androidx.core.view.marginTop
import com.example.battletanks.enums.Direction.UP
import com.example.battletanks.enums.Direction.DOWN
import com.example.battletanks.enums.Direction.LEFT
import com.example.battletanks.enums.Direction.RIGHT
import com.example.battletanks.databinding.ActivityMainBinding
import com.example.battletanks.drawers.ElementsDrawer
import com.example.battletanks.drawers.GridDrawer
import com.example.battletanks.enums.Direction
import com.example.battletanks.enums.Material
import com.example.battletanks.models.Coordinate

const val CELL_SIZE = 50

lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var editMode = false
    private val gridDrawer by lazy {
        GridDrawer(binding.container)
    }

    private val elementsDrawer by lazy {
        ElementsDrawer(binding.container)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Menu"

        binding.editorClear.setOnClickListener { elementsDrawer.currentMaterial = Material.EMPTY }
        binding.editorBrick.setOnClickListener { elementsDrawer.currentMaterial = Material.BRICK }
        binding.editorConcrete.setOnClickListener {
            elementsDrawer.currentMaterial = Material.CONCRETE
        }
        binding.editorGrass.setOnClickListener { elementsDrawer.currentMaterial = Material.GRASS }
        binding.container.setOnTouchListener { _, event ->
            elementsDrawer.onTouchContainer(event.x, event.y)
            return@setOnTouchListener true
        }
    }

    private fun switchEditMode() {
        if (editMode) {
            gridDrawer.removeGrid()
            binding.materialsContainer.visibility = INVISIBLE
        } else {
            gridDrawer.drawGrid()
            binding.materialsContainer.visibility = VISIBLE
        }
        editMode = !editMode
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.settings, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_settings -> {
                switchEditMode()
                return true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        when (keyCode) {
            KEYCODE_DPAD_UP -> move(UP)
            KEYCODE_DPAD_DOWN -> move(DOWN)
            KEYCODE_DPAD_LEFT -> move(LEFT)
            KEYCODE_DPAD_RIGHT -> move(RIGHT)
        }
        return super.onKeyDown(keyCode, event)
    }

    private fun move(direction: Direction) {
        when(direction){
            UP->{
                binding.myTank.rotation = 0f
                if (binding.myTank.marginTop > 0) {
                    (binding.myTank.layoutParams as FrameLayout.LayoutParams).topMargin += -CELL_SIZE
                }
            }

            DOWN->{
                binding.myTank.rotation = 180f
                if (binding.myTank.marginTop + binding.myTank.height < binding.container.height / CELL_SIZE * CELL_SIZE) {
                    (binding.myTank.layoutParams as FrameLayout.LayoutParams).topMargin += CELL_SIZE
                }
            }

            LEFT->{
                binding.myTank.rotation = 270f
                if (binding.myTank.marginLeft > 0) {
                    (binding.myTank.layoutParams as FrameLayout.LayoutParams).leftMargin -= CELL_SIZE
                }
            }

            RIGHT->{
                binding.myTank.rotation = 90f
                if (binding.myTank.marginLeft + binding.myTank.width < binding.container.width / CELL_SIZE * CELL_SIZE) {
                    (binding.myTank.layoutParams as FrameLayout.LayoutParams).leftMargin += CELL_SIZE
                }
            }
        }
        binding.container.removeView(binding.myTank)
        binding.container.addView(binding.myTank)
    }
}