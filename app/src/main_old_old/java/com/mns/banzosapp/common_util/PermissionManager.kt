package com.mns.banzosapp.common_util

import android.Manifest
import android.R
import android.annotation.TargetApi
import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


object PermissionManager {
    const val MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123
    const val MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 124
    const val MY_PERMISSIONS_REQUEST_LOCATION_ACCESS = 125
    const val MY_PERMISSIONS_REQUEST_READ_CONTACT = 126
    const val MY_PERMISSIONS_REQUEST_CAMERA_ = 2

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    fun checkStoragePermission(context: Activity?): Boolean {
        val currentAPIVersion = Build.VERSION.SDK_INT
        return if (currentAPIVersion >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(
                    context!!,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) !== PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(
                    context!!,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) !== PackageManager.PERMISSION_GRANTED
            ) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        context!!,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    )
                ) {
                    val alertBuilder: AlertDialog.Builder = AlertDialog.Builder(context)
                    alertBuilder.setCancelable(true)
                    alertBuilder.setTitle("Permission necessary")
                    alertBuilder.setMessage("External storage permission is necessary")
                    alertBuilder.setPositiveButton(
                        R.string.yes,
                        DialogInterface.OnClickListener { dialog, which ->
                            ActivityCompat.requestPermissions(
                                context,
                                arrayOf<String>(
                                    Manifest.permission.READ_EXTERNAL_STORAGE,
                                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                                ),
                                MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE
                            )
                        })
                    val alert: AlertDialog = alertBuilder.create()
                    alert.show()
                } else {
                    ActivityCompat.requestPermissions(
                        context,
                        arrayOf<String>(
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                        ),
                        MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE
                    )
                }
                false
            } else {
                true
            }
        } else {
            true
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    fun checkContactPermission(context: Activity?): Boolean {
        val currentAPIVersion = Build.VERSION.SDK_INT
        return if (currentAPIVersion >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(
                    context!!,
                    Manifest.permission.READ_CONTACTS
                ) !== PackageManager.PERMISSION_GRANTED
            ) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        context!!,
                        Manifest.permission.READ_CONTACTS
                    )
                ) {
                    val alertBuilder: AlertDialog.Builder = AlertDialog.Builder(context)
                    alertBuilder.setCancelable(true)
                    alertBuilder.setTitle("Permission necessary")
                    alertBuilder.setMessage("Contact permission is necessary")
                    alertBuilder.setPositiveButton(
                        R.string.yes,
                        DialogInterface.OnClickListener { dialog, which ->
                            ActivityCompat.requestPermissions(
                                context,
                                arrayOf<String>(Manifest.permission.READ_CONTACTS),
                                MY_PERMISSIONS_REQUEST_READ_CONTACT
                            )
                        })
                    val alert: AlertDialog = alertBuilder.create()
                    alert.show()
                } else {
                    ActivityCompat.requestPermissions(
                        context,
                        arrayOf<String>(Manifest.permission.READ_CONTACTS),
                        MY_PERMISSIONS_REQUEST_READ_CONTACT
                    )
                }
                false
            } else {
                true
            }
        } else {
            true
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    fun checkWriteStoragePermission(context: Activity?): Boolean {
        val currentAPIVersion = Build.VERSION.SDK_INT
        return if (currentAPIVersion >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(
                    context!!,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) !== PackageManager.PERMISSION_GRANTED
            ) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        context!!,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    )
                ) {
                    val alertBuilder: AlertDialog.Builder = AlertDialog.Builder(context)
                    alertBuilder.setCancelable(true)
                    alertBuilder.setTitle("Permission necessary")
                    alertBuilder.setMessage("External storage permission is necessary")
                    alertBuilder.setPositiveButton(
                        R.string.yes,
                        DialogInterface.OnClickListener { dialog, which ->
                            ActivityCompat.requestPermissions(
                                context,
                                arrayOf<String>(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                                MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE
                            )
                        })
                    val alert: AlertDialog = alertBuilder.create()
                    alert.show()
                } else {
                    ActivityCompat.requestPermissions(
                        context,
                        arrayOf<String>(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                        MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE
                    )
                }
                false
            } else {
                true
            }
        } else {
            true
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    fun checkCameraStoragePermission(context: Activity?): Boolean {
        val currentAPIVersion = Build.VERSION.SDK_INT
        return if (currentAPIVersion >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(
                    context!!,
                    arrayOf(
                        Manifest.permission.CAMERA,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    ).toString()
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        context,
                        arrayOf(
                            Manifest.permission.CAMERA,
                            Manifest.permission.READ_EXTERNAL_STORAGE
                        ).toString()
                    )
                ) {
                    val alertBuilder: AlertDialog.Builder = AlertDialog.Builder(context)
                    alertBuilder.setCancelable(true)
                    alertBuilder.setTitle("Permission necessary")
                    alertBuilder.setMessage("Camera permission is necessary")
                    alertBuilder.setPositiveButton(
                        R.string.yes,
                        DialogInterface.OnClickListener { dialog, which ->
                            ActivityCompat.requestPermissions(
                                context,
                                arrayOf<String>(Manifest.permission.CAMERA),
                                MY_PERMISSIONS_REQUEST_CAMERA_
                            )
                        })
                    val alert: AlertDialog = alertBuilder.create()
                    alert.show()
                } else {
                    ActivityCompat.requestPermissions(
                        context,
                        arrayOf<String>(
                            Manifest.permission.CAMERA,
                            Manifest.permission.READ_EXTERNAL_STORAGE
                        ),
                        MY_PERMISSIONS_REQUEST_CAMERA_
                    )
                }
                false
            } else {
                true
            }
        } else {
            true
        }
    }

    fun checkLocationPermission(context: Activity?): Boolean {
        val currentAPIVersion = Build.VERSION.SDK_INT
        return if (currentAPIVersion >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(
                    context!!,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) !== PackageManager.PERMISSION_GRANTED
            ) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        context!!,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    )
                ) {

                    /* AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
                                   alertBuilder.setCancelable(true);
                                   alertBuilder.setTitle("Permission necessary");
                                   alertBuilder.setMessage("Location permission is necessary");
                                   alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                       @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                                       public void onClick(DialogInterface dialog, int which) {*/
                    ActivityCompat.requestPermissions(
                        context,
                        arrayOf<String>(Manifest.permission.ACCESS_FINE_LOCATION),
                        MY_PERMISSIONS_REQUEST_LOCATION_ACCESS
                    )
                    /*  }
                                   });
                                   alertBuilder.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                       @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                                       public void onClick(DialogInterface dialog, int which) {
                                           context.finish();
                                       }
                                   });
                                   AlertDialog alert = alertBuilder.create();
                                   alert.show();*/
                } else {
                    ActivityCompat.requestPermissions(
                        context,
                        arrayOf<String>(Manifest.permission.ACCESS_FINE_LOCATION),
                        MY_PERMISSIONS_REQUEST_LOCATION_ACCESS
                    )
                }
                false
            } else {
                true
            }
        } else {
            true
        }
    }
}