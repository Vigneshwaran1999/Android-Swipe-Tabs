Android Swipe Tabs
==========

Just swipe tabs on fragments. Simplest demo.

Individual class (extended Fragment) for each tab and correct work with FragmentManager in activity class.

If you use google maps in one of tabs, use SupportMapFragment:
GoogleMap mMap = ((SupportMapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();

//Android Studio project.
