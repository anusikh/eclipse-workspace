Ext.onReady(function() {
	Ext.create('Ext.container.Viewport', {
		layout: {
            type: 'vbox',
            align: 'stretch'
        },
		items: [{
			region: 'north',
			flex: 1,
			title: 'Movie Advance Search',
			items: [{
	            fieldLabel: 'Movie Name',
	            xtype: 'textfield'
	        }],
		}, {
			region: 'south',
			flex: 1,
			title: 'Movie Grid',
		}]
	});
})