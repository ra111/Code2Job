package com.studyx.urgames.code2job

import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Matrix
import android.graphics.PointF
import android.graphics.pdf.PdfRenderer
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.os.ParcelFileDescriptor
import android.support.annotation.RequiresApi
import android.support.v4.app.ActivityCompat
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewStub
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException

/**
 * Created by rahula on 20/01/18.
 */

class imp_topics : Activity(), ActivityCompat.OnRequestPermissionsResultCallback, View.OnTouchListener {
    internal lateinit var pdfView: ImageView
    private var currentPage: PdfRenderer.Page? = null
    internal lateinit  var btnPrevious: Button
    internal lateinit var btnNext: Button
    internal var i: Int? = 0
    internal var pdfRenderer: PdfRenderer? = null
    internal var matrix = Matrix()
    internal var savedMatrix = Matrix()
    internal var mode = NONE

    // these PointF objects are used to record the point(s) the user is touching
    internal var start = PointF()
    internal var mid = PointF()
    internal var oldDist = 1f
    internal val database = FirebaseDatabase.getInstance()

    internal val mDatabase = database.reference
    internal var current_ref = mDatabase.child("users").child(FirebaseAuth.getInstance().currentUser!!.uid).child("freemium")
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.imp_topic)
        requestPermission()
        pdfView = findViewById<View>(R.id.pdfview) as ImageView
        pdfView.setOnTouchListener(this)
        btnPrevious = findViewById<View>(R.id.btn_previous) as Button
        btnNext = findViewById<View>(R.id.btn_next) as Button

        //set buttons event
        btnPrevious.setOnClickListener {
            //
            val current_ref = mDatabase.child("users").child(FirebaseAuth.getInstance().currentUser!!.uid).child("freemium")
            current_ref.addListenerForSingleValueEvent(object : ValueEventListener {
                @SuppressLint("NewApi")
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val current = dataSnapshot.getValue<String>(String::class.java)
                    val prev = Integer.parseInt(current) - 1
                    mDatabase.child("users").child(FirebaseAuth.getInstance().currentUser!!.uid).child("freemium").setValue(Integer.toString(prev))
                    showPage(prev)


                }

                override fun onCancelled(databaseError: DatabaseError) {

                }
            })
        }


        btnNext.setOnClickListener {
            val current_ref = mDatabase.child("users").child(FirebaseAuth.getInstance().currentUser!!.uid).child("freemium")
            current_ref.addListenerForSingleValueEvent(object : ValueEventListener {
                @SuppressLint("NewApi")
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val current = dataSnapshot.getValue<String>(String::class.java)
                    val next = Integer.parseInt(current) + 1

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        showPage(next)
                    }
                    mDatabase.child("users").child(FirebaseAuth.getInstance().currentUser!!.uid).child("freemium").setValue(Integer.toString(next))
                }

                override fun onCancelled(databaseError: DatabaseError) {

                }
            })
        }


    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == REQUEST_WRITE_PERMISSION && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            writeFile()
        }
    }

    private fun requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE), REQUEST_WRITE_PERMISSION)
        } else {
            writeFile()
        }
    }

    fun writeFile() {
        val path = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOCUMENTS)
        val localFile = File(path, "/Elitmus.pdf")
        try {
            localFile.createNewFile()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        if (!path.exists()) {
            path.mkdirs()
        }
        download(localFile)

    }

    fun download(localFile: File) {

        val storage = FirebaseStorage.getInstance()
        val storageRef = storage.getReference("Elitmus.pdf")
        val importPanel = (findViewById<View>(R.id.stub_import) as ViewStub).inflate()
        storageRef.getFile(localFile).addOnSuccessListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                read(localFile)
            }
            importPanel.visibility = View.GONE
            // If there is a savedInstanceState (screen orientations, etc.), we restore the page index.
        }.addOnFailureListener { exception ->
                    // Handle any errors
                    Toast.makeText(baseContext, "Eroor " + exception.message, Toast.LENGTH_LONG).show()
                }

    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    fun read(localFile: File) {

        var fileDescriptor: ParcelFileDescriptor? = null
        try {
            fileDescriptor = ParcelFileDescriptor.open(
                    localFile, ParcelFileDescriptor.MODE_READ_ONLY)
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }

        //min. API Level 21

        try {
            pdfRenderer = PdfRenderer(fileDescriptor!!)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        current_ref.addListenerForSingleValueEvent(object : ValueEventListener {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val current = dataSnapshot.getValue<String>(String::class.java)

                showPage(Integer.parseInt(current))
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })


    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private fun showPage(index: Int) {
        if (pdfRenderer!!.pageCount <= index) {
            return
        }
        // Make sure to close the current page before opening another one.
        if (null != currentPage) {
            currentPage!!.close()
        }
        //open a specific page in PDF file
        currentPage = pdfRenderer!!.openPage(index)
        // Important: the destination bitmap must be ARGB (not RGB).
        val bitmap = Bitmap.createBitmap(currentPage!!.width, currentPage!!.height,
                Bitmap.Config.ARGB_8888)
        // Here, we render the page onto the Bitmap.
        currentPage!!.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY)
        // showing bitmap to an imageview
        pdfView.setImageBitmap(bitmap)
        updateUIData()
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private fun updateUIData() {
        val index = currentPage!!.index
        val pageCount = pdfRenderer!!.pageCount
        btnPrevious.isEnabled = 0 != index
        btnNext.isEnabled = index + 1 < pageCount

    }

    override fun onTouch(v: View, event: MotionEvent): Boolean {
        val view = v as ImageView
        view.scaleType = ImageView.ScaleType.MATRIX
        val scale: Float

        dumpEvent(event)
        // Handle touch events here...

        when (event.action and MotionEvent.ACTION_MASK) {
            MotionEvent.ACTION_DOWN   // first finger down only
            -> {
                savedMatrix.set(matrix)
                start.set(event.x, event.y)
                Log.d(TAG, "mode=DRAG") // write to LogCat
                mode = DRAG
            }

            MotionEvent.ACTION_UP // first finger lifted
                ,

            MotionEvent.ACTION_POINTER_UP // second finger lifted
            -> {

                mode = NONE
                Log.d(TAG, "mode=NONE")
            }

            MotionEvent.ACTION_POINTER_DOWN // first and second finger down
            -> {

                oldDist = spacing(event)
                Log.d(TAG, "oldDist=" + oldDist)
                if (oldDist > 5f) {
                    savedMatrix.set(matrix)
                    midPoint(mid, event)
                    mode = ZOOM
                    Log.d(TAG, "mode=ZOOM")
                }
            }

            MotionEvent.ACTION_MOVE ->

                if (mode == DRAG) {
                    matrix.set(savedMatrix)
                    matrix.postTranslate(event.x - start.x, event.y - start.y) // create the transformation in the matrix  of points
                } else if (mode == ZOOM) {
                    // pinch zooming
                    val newDist = spacing(event)
                    Log.d(TAG, "newDist=" + newDist)
                    if (newDist > 5f) {
                        matrix.set(savedMatrix)
                        scale = newDist / oldDist // setting the scaling of the
                        // matrix...if scale > 1 means
                        // zoom in...if scale < 1 means
                        // zoom out
                        matrix.postScale(scale, scale, mid.x, mid.y)
                    }
                }
        }

        view.imageMatrix = matrix // display the transformation on screen

        return true // indicate event was handled

    }

    private fun spacing(event: MotionEvent): Float {
        val x = event.getX(0) - event.getX(1)
        val y = event.getY(0) - event.getY(1)
        return Math.sqrt((x * x + y * y).toDouble()).toFloat()
    }

    private fun midPoint(point: PointF, event: MotionEvent) {
        val x = event.getX(0) + event.getX(1)
        val y = event.getY(0) + event.getY(1)
        point.set(x / 2, y / 2)
    }

    private fun dumpEvent(event: MotionEvent) {
        val names = arrayOf("DOWN", "UP", "MOVE", "CANCEL", "OUTSIDE", "POINTER_DOWN", "POINTER_UP", "7?", "8?", "9?")
        val sb = StringBuilder()
        val action = event.action
        val actionCode = action and MotionEvent.ACTION_MASK
        sb.append("event ACTION_").append(names[actionCode])

        if (actionCode == MotionEvent.ACTION_POINTER_DOWN || actionCode == MotionEvent.ACTION_POINTER_UP) {
            sb.append("(pid ").append(action shr MotionEvent.ACTION_POINTER_ID_SHIFT)
            sb.append(")")
        }

        sb.append("[")
        for (i in 0 until event.pointerCount) {
            sb.append("#").append(i)
            sb.append("(pid ").append(event.getPointerId(i))
            sb.append(")=").append(event.getX(i).toInt())
            sb.append(",").append(event.getY(i).toInt())
            if (i + 1 < event.pointerCount)
                sb.append(";")
        }

        sb.append("]")

    }

    companion object {

        private val REQUEST_WRITE_PERMISSION = 786
        private val TAG = "Touch"
        private val MIN_ZOOM = 1f
        private val MAX_ZOOM = 1f

        // The 3 states (events) which the user is trying to perform
        internal val NONE = 0
        internal val DRAG = 1
        internal val ZOOM = 2
    }

}




